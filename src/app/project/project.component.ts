import {AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {ProjectDto, ProjectHeadingDto, ProjectService} from "./project.service";
import {AgGridAngular} from "ag-grid-angular";
import {ItemDeleteComponent} from "../common/item-delete.component";
import {ItemAddComponent} from "../common/item-add.component";
import {UserService} from "../user/user.service";
import {LabelWithId, Role} from "../common/common-classes";
import {Subscription} from "rxjs";

@Component({
    selector: 'projects',
    template: `
        <ag-grid-angular
                #agGrid
                class="ag-theme-alpine"
                style="width: 100%"
                domLayout='autoHeight'
                rowSelection='single'
                [rowData]="projects"
                [columnDefs]="columnDefs"
                (firstDataRendered)="onFirstDataRendered($event)"
        >
        </ag-grid-angular>
        <div #addProjectModal id="addProjectModal" class="modal fade" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Editing Project</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="form-outline form-white mb-4">
                            <input type="text" id="typeNameX"
                                   class="form-control form-control-lg"
                                   [(ngModel)]="editedProject.name"/>
                            <label class="form-label" for="typeNameX">Name</label>
                        </div>
                        <div class="form-outline form-white mb-4">
                            <input type="text" id="typeCodeX"
                                   class="form-control form-control-lg"
                                   [(ngModel)]="editedProject.code"/>
                            <label class="form-label" for="typeCodeX">Code</label>
                        </div>
                        <div class="form-outline form-white mb-4">
                            <simple-dropdown
                                    id="typeDropdownX"
                                    [items]="managers"
                                    [selectedItemId]="editedProject.projectManagerId"
                                    [title]="'Select Project Manager'"
                                    (itemChange)="onManagerChanged($event)">
                            </simple-dropdown>
                            <label class="form-label" for="typePasswordX">Project Manager</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button"
                                class="btn btn-secondary btn-lg px-5"
                                (click)="dismissChanges()"
                                data-bs-dismiss="modal">Close
                        </button>
                        <button type="submit"
                                class="btn btn-primary btn-lg px-5"
                                (click)="saveChanges()"
                                data-bs-dismiss="modal">Save changes
                        </button>
                    </div>
                </div>
            </div>
        </div>
    `,
    styles: []
})
export class ProjectComponent implements OnInit, AfterViewInit, OnDestroy {

    @ViewChild('agGrid') agGrid: AgGridAngular;
    @ViewChild('addProjectModal') addProjectModal: ElementRef;

    private subscriptions: Subscription[] = [];
    projects: ProjectHeadingDto[];
    managers: LabelWithId[];
    editedProject: ProjectDto = <ProjectDto>{};
    selectedManager: LabelWithId;

    columnDefs = [
        {field: 'name', resizable: true, width: 80},
        {field: 'code', resizable: true, width: 50},
        {field: 'projectManagerName', resizable: true, width: 80},
        {
            width: 5,
            cellRendererFramework: ItemDeleteComponent,
            cellRendererParams: {
                deletionFunction: (id: number) => this.projectService.delete(id)
                    .subscribe(() => this.fetchProjectsData())
            }
        },
        {
            width: 5,
            headerComponentFramework: ItemAddComponent,
            headerComponentParams: {
                addModalName: "addProjectModal"
            },
            cellRendererFramework: ItemDeleteComponent,
            cellRendererParams: {
                deletionFunction: (id: number) => this.projectService.delete(id)
                    .subscribe(() => this.fetchProjectsData())
            }
        }
    ];

    constructor(private projectService: ProjectService,
                private userService: UserService) {
    }

    onFirstDataRendered(params) {
        params.api.sizeColumnsToFit();
    }

    ngOnInit(): void {
        this.fetchProjectsData();
        this.subscriptions.push(
            this.userService.getUsersLabelsOfType(Role.PROJECT_MANAGER)
                .subscribe(managers => {
                    this.managers = managers;
                }));
    }

    ngAfterViewInit(): void {
        setTimeout(() => {
            this.agGrid.api.sizeColumnsToFit();
        }, 300);
    }

    ngOnDestroy(): void {
        this.subscriptions.forEach(s => s.unsubscribe());
    }

    fetchProjectsData() {
        this.subscriptions.push(this.projectService.getProjects().subscribe(projects => {
            this.projects = projects;
        }));
    }

    onAddItem() {
        this.editedProject = <ProjectDto>{};
    }


    saveChanges() {
        if (this.editedProject.id) {
            this.projectService.updateProject(this.editedProject).subscribe(() => {
                this.fetchProjectsData();
            });
        } else {
            this.projectService.createProject(this.editedProject).subscribe(() => {
                this.fetchProjectsData();
            });
        }
    }

    onManagerChanged(managerLabelWithId: LabelWithId) {
        this.editedProject.projectManagerId = managerLabelWithId.id;
    }

    dismissChanges() {
        this.editedProject = <ProjectDto>{};
    }
}
