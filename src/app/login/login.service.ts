import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, Subscription} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    constructor(private http: HttpClient) {
    }

    private onLogicSuccessSubscription: Subscription;
    private onLogicErrorSubscription: Subscription;

    login(request: LoginRequest): Observable<LoginResult> {
        return this.http.post<LoginResult>("api/login", this.createData(request), {headers: new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'})});
    }

    private createData(request: LoginRequest): HttpParams {
        let data = new HttpParams();
        return data.append("username", request.username).append("password", request.password);
    }

    public subscribeToLoginSuccess(emitter: EventEmitter<any>) {
        this.onLogicSuccessSubscription = emitter.subscribe();
    }

    public subscribeToLoginError(emitter: EventEmitter<any>) {
        this.onLogicErrorSubscription = emitter.subscribe();
    }

    public unsubscribe(){
        this.onLogicSuccessSubscription.unsubscribe();
        this.onLogicErrorSubscription.unsubscribe();
    }
}

export interface LoginRequest {
    username: string;
    password: string;
}

export interface LoginResult {
    code: string;
}
