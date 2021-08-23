import {Component, ElementRef, EventEmitter, OnDestroy, OnInit, Output, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {LoginService} from "./login.service";

@Component({
    selector: 'app-login',
    template: `
        <section class="vh-100 gradient-custom">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div class="card bg-primary text-white" style="border-radius: 1rem;">
                            <div class="card-body p-5 text-center">
                                <div class="mb-md-5 mt-md-4 pb-5">
                                    <h2 class="fw-bold mb-2 text-uppercase">Login</h2>
                                    <form #loginForm="ngForm" (ngSubmit)="login($event)">
                                        <div class="form-outline form-white mb-4">
                                            <input #usernameInput type="email" id="typeEmailX" 
                                                   class="form-control form-control-lg"
                                                   [(ngModel)]="username"/>
                                            <label class="form-label" for="typeEmailX">Email</label>
                                        </div>
                                        <div class="form-outline form-white mb-4">
                                            <input #passwordInput type="password" id="typePasswordX"
                                                   class="form-control form-control-lg"
                                                   [(ngModel)]="password"/>
                                            <label class="form-label" for="typePasswordX">Password</label>
                                        </div>
                                        <button class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    `,
    styles: []
})
export class LoginComponent implements OnInit, OnDestroy {

    @ViewChild("usernameInput", {static: true})
    private usernameField: ElementRef;
    @ViewChild("passwordInput", {static: true})
    private passwordField: ElementRef;
    @Output()
    onLoginSuccess: EventEmitter<any> = new EventEmitter<any>();
    @Output()
    onLoginError: EventEmitter<any> = new EventEmitter<any>();

    authenticationError: boolean = false;
    username: string = "";
    password: string = "";
    loginInProgress: boolean = false;

    constructor(private loginService: LoginService, private http: HttpClient, private router: Router) {
    }

    ngOnInit(): void {
        this.focus();
        this.loginService.subscribeToLoginSuccess(this.onLoginSuccess);
        this.loginService.subscribeToLoginError(this.onLoginError);
    }

    ngOnDestroy(): void {
        this.loginService.unsubscribe();
    }


    private focus() {
        if (this.username) {
            setTimeout(() => this.passwordField.nativeElement.focus());
        } else {
            setTimeout(() => this.usernameField.nativeElement.focus());
        }
    }

    login($event: any) {
        $event.preventDefault();
        this.loginInProgress = true;
        this.loginService.login({username: this.username, password: this.password}).subscribe(
            result => {
                if (result.code == 'SUCCESS') {
                    this.onLoginSuccess.emit();
                    this.router.navigateByUrl('/projects')
                } else {
                    this.onLoginError.emit();
                }
            },
            error => {
                this.onLoginError.emit();
                this.authenticationError = true;
            });
    }

}
