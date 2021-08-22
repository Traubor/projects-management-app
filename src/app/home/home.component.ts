import {Component} from '@angular/core';

@Component({
    selector: 'app-projects',
    template: `
        <div class="container pb-5">
            <ul class="nav nav-pills nav-fill">
                <a class="nav-link" routerLinkActive="active" routerLink="/projects">Projects</a>
                <a class="nav-link" routerLinkActive="active" routerLink="/tasks">Tasks</a>
                <a class="nav-link" routerLinkActive="active" routerLink="/users">Users</a>
            </ul>
        </div>
        <div class="container" style="height: 500px">
            <router-outlet></router-outlet>
        </div>
    `,
    styles: []
})
export class HomeComponent {

}
