import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
    selector: 'app-root',
    template: `
        <div class="container" style="height: 500px">
            <router-outlet></router-outlet>
        </div>
    `,
    styles: []
})
export class AppComponent {
    authenticated = false;

    constructor(private http: HttpClient, private router: Router) {

    }

    logout() {
        this.http.post('logout', {}).subscribe(() => {
            // this.app.authenticated = false;
            this.router.navigateByUrl('/login');
        });
    }

    onSuccessfulLogin() {
        this.authenticated = true;
    }
}
