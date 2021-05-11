import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISisyphusJobParam, SisyphusJobParam } from '../sisyphus-job-param.model';
import { SisyphusJobParamService } from '../service/sisyphus-job-param.service';

@Injectable({ providedIn: 'root' })
export class SisyphusJobParamRoutingResolveService implements Resolve<ISisyphusJobParam> {
  constructor(protected service: SisyphusJobParamService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISisyphusJobParam> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sisyphusJobParam: HttpResponse<SisyphusJobParam>) => {
          if (sisyphusJobParam.body) {
            return of(sisyphusJobParam.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SisyphusJobParam());
  }
}
