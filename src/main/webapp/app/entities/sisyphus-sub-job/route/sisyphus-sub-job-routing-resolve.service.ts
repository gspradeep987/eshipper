import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISisyphusSubJob, SisyphusSubJob } from '../sisyphus-sub-job.model';
import { SisyphusSubJobService } from '../service/sisyphus-sub-job.service';

@Injectable({ providedIn: 'root' })
export class SisyphusSubJobRoutingResolveService implements Resolve<ISisyphusSubJob> {
  constructor(protected service: SisyphusSubJobService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISisyphusSubJob> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sisyphusSubJob: HttpResponse<SisyphusSubJob>) => {
          if (sisyphusSubJob.body) {
            return of(sisyphusSubJob.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SisyphusSubJob());
  }
}
