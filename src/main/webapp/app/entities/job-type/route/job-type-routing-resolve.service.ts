import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IJobType, JobType } from '../job-type.model';
import { JobTypeService } from '../service/job-type.service';

@Injectable({ providedIn: 'root' })
export class JobTypeRoutingResolveService implements Resolve<IJobType> {
  constructor(protected service: JobTypeService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IJobType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((jobType: HttpResponse<JobType>) => {
          if (jobType.body) {
            return of(jobType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new JobType());
  }
}
