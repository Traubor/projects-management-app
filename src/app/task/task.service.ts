import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class TaskService {

    constructor(private http: HttpClient) {
    }

    getTasks(): Observable<TaskDto[]> {
        return this.http.get<TaskDto[]>("api/task/all");
    }

}

export interface TaskDto {
    id: number;
    description: string;
}
