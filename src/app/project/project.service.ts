import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class ProjectService {

    constructor(private http: HttpClient) {
    }

    getProjects(): Observable<ProjectHeadingDto[]> {
        return this.http.get<ProjectHeadingDto[]>("api/project/all");
    }

    delete(projectId: number): Observable<any> {
        return this.http.delete("api/project/delete/" + projectId);
    }

    updateProject(projectDto: ProjectDto): Observable<any> {
        console.log("updated!")
        return this.http.post("api/project/update", projectDto);
    }

    createProject(projectDto: ProjectDto): Observable<any> {
        console.log("new!")
        return this.http.put("api/project/create", projectDto);
    }
}

export interface ProjectHeadingDto {
    id: number;
    name: string;
    code: string;
    projectManagerName: string;
}

export interface ProjectDto {
    id: number;
    name: string;
    code: string;
    projectManagerId: number;
}
