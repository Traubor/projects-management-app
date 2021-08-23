import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';


import {HttpClientModule} from "@angular/common/http";

import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";

import {AgGridModule} from "ag-grid-angular";
import {ProjectComponent} from "../project/project.component";
import {TaskComponent} from "../task/task.component";
import {HomeComponent} from "./home.component";
import {ItemDeleteComponent} from "../common/item-delete.component";
import {ItemAddComponent} from "../common/item-add.component";
import {ProjectService} from "../project/project.service";
import {TaskService} from "../task/task.service";
import {UserService} from "../user/user.service";
import {DropdownComponent} from "../common/dropdown.component";


const routes: Routes = [
    {
        path: '', component: HomeComponent,
        children: [
            {path: 'projects', component: ProjectComponent},
            {path: 'tasks', component: TaskComponent},
            {path: "", redirectTo: 'projects', pathMatch: 'full'},
        ]
    },
];

@NgModule({
    declarations: [
        HomeComponent,
        ProjectComponent,
        TaskComponent,
        ItemDeleteComponent,
        ItemAddComponent,
        DropdownComponent
    ],
    imports: [
        RouterModule.forChild(routes),
        BrowserModule,
        HttpClientModule,
        FormsModule,
        AgGridModule.withComponents([])
    ],
    providers: [ProjectService, TaskService, UserService],
    bootstrap: [HomeComponent]
})
export class HomeModule {
}
