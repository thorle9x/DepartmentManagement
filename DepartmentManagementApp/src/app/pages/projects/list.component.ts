import {Component, OnInit} from '@angular/core';
import {ProjectsService} from '../../data/services/projects.service';
import {first} from 'rxjs/operators';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  templateUrl: './list.component.html'
})
export class ListComponent implements OnInit {
  projects = null;

  constructor(private projectsService: ProjectsService, private nzMessageService: NzMessageService) {
  }

  ngOnInit() {
    this.projectsService.getAll()
      .pipe(first())
      .subscribe(
        projects => {
          this.projects = projects;
        },
        error => {
        }
      );
  }

  toggleProjectStatus(projectId: bigint, newStatus: boolean) {
    const project = this.projects.find(x => x.id === projectId);
    project.active = newStatus;
    this.projectsService.updateProject(project)
      .pipe(first())
      .subscribe(
        data => {
          console.log(data);
          this.nzMessageService.info('Action successful');
        },
        error => {
          console.log(error);
          this.nzMessageService.error(error);
        }
      );
  }
}
