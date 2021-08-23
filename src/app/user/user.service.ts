import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {LabelWithId, Role} from "../common/common-classes";

@Injectable({
    providedIn: 'root'
})
export class UserService {

    constructor(private http: HttpClient) {
    }

    getUsersLabelsOfType(role: Role): Observable<LabelWithId[]> {
        return this.http.get<LabelWithId[]>("api/user/labels/" + role);
    }

    delete(userId: number): Observable<any> {
        return this.http.delete("api/user/delete/" + userId);
    }
}

export interface UserDto {
    id: number;
    name: string;
    surname: string;
    email: string;
}
