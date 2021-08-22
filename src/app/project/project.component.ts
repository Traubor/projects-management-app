import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ProjectHeadingDto, ProjectService} from "./project.service";
import {AgGridAngular} from "ag-grid-angular";
import {ItemDeleteComponent} from "../common/item-delete.component";
import {ItemAddComponent} from "../common/item-add.component";

@Component({
    selector: 'app-projects',
    template: `
        <ag-grid-angular
                #agGrid
                class="ag-theme-alpine"
                style="width: 100%"
                domLayout='autoHeight'
                [rowData]="projects"
                [columnDefs]="columnDefs"
                (firstDataRendered)="onFirstDataRendered($event)">
        </ag-grid-angular>
    `,
    styles: []
})
export class ProjectComponent implements OnInit, AfterViewInit {

    @ViewChild('agGrid') agGrid: AgGridAngular;

    projects: ProjectHeadingDto[];

    columnDefs = [
        {field: 'name', resizable: true, width: 80},
        {field: 'code', resizable: true, width: 50},
        {field: 'projectManagerName', resizable: true, width: 80},
        {
            width: 5,
            headerComponentFramework: ItemAddComponent,
            headerComponentParams: {
                addFunction: () => this.addItem()
            },
            cellRendererFramework: ItemDeleteComponent,
            cellRendererParams: {
                deletionFunction: (id: number) => this.projectService.delete(id)
                    .subscribe(() => this.fetchData())
            }
        }
    ];

    constructor(private projectService: ProjectService) {
    }

    onFirstDataRendered(params) {
        params.api.sizeColumnsToFit();
    }

    ngOnInit(): void {
        this.fetchData()
    }

    fetchData() {
        this.projectService.getProjects().subscribe(projects => {
            this.projects = projects;
        })
    }

    ngAfterViewInit(): void {
        setTimeout(() => {
            this.agGrid.api.sizeColumnsToFit();
        }, 300);
    }

    addItem() {
        console.log("Add item!!! :)")
    }

}
