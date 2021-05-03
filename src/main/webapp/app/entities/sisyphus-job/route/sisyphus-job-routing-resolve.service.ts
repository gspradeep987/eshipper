import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISisyphusJob, SisyphusJob } from '../sisyphus-job.model';
import { SisyphusJobService } from '../service/sisyphus-job.service';

@Injectable({ providedIn: 'root' })
export class SisyphusJobRoutingResolveService implements Resolve<ISisyphusJob> {
  constructor(protected service: SisyphusJobService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISisyphusJob> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sisyphusJob: HttpResponse<SisyphusJob>) => {
          if (sisyphusJob.body) {
            return of(sisyphusJob.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SisyphusJob());
  }
}
