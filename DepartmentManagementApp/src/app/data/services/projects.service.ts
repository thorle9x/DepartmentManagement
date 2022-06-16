import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {API_URL} from '../app.constants';
import {map} from 'rxjs/operators';
import {Project} from '../../domain/model/project';

@Injectable({
  providedIn: 'root'
})
export class ProjectsService {
  constructor(private httpClient: HttpClient) {
  }

  getAll() {
    return this.httpClient.get<Project[]>(
      `${API_URL}/department`
    ).pipe(
      map(projects => {
        return projects;
      })
    );
  }

  getById(projectId: number) {
    return this.httpClient.get<Project>(
      `${API_URL}/department/${projectId}`
    ).pipe(
      map(project => {
        console.log(project);
        return project;
      })
    );
  }

  createProject(project: Project) {
    return this.httpClient.post(
      `${API_URL}/department`,
      project
    ).pipe(
      map(data => {
        return data;
      })
    );
  }

  updateProject(project: Project) {
    return this.httpClient.put(
      `${API_URL}/department/${project.id}`,
      project
    ).pipe(
      map(data => {
        return data;
      })
    );
  }
}
