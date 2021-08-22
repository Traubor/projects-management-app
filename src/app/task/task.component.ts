import {Component, OnInit} from '@angular/core';
import {TaskDto, TaskService} from "./task.service";

@Component({
    selector: 'app-projects',
    template: `
        <div>
            <ul class="list-group">
                <li class="list-group-item" *ngFor="let task of tasks">{{task.description}}</li>
            </ul>
        </div>
    `,
    styles: []
})
export class TaskComponent implements OnInit {

    constructor(private taskService: TaskService) {
    }

    tasks: TaskDto[];

    ngOnInit(): void {
        this.taskService.getTasks().subscribe(tasks => {
            this.tasks = tasks;
        })
    }

}
