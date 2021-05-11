import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISisyphusJobType, SisyphusJobType } from '../sisyphus-job-type.model';
import { SisyphusJobTypeService } from '../service/sisyphus-job-type.service';

@Injectable({ providedIn: 'root' })
export class SisyphusJobTypeRoutingResolveService implements Resolve<ISisyphusJobType> {
  constructor(protected service: SisyphusJobTypeService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISisyphusJobType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sisyphusJobType: HttpResponse<SisyphusJobType>) => {
          if (sisyphusJobType.body) {
            return of(sisyphusJobType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SisyphusJobType());
  }
}
