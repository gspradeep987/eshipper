import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISisyphusClasses, SisyphusClasses } from '../sisyphus-classes.model';
import { SisyphusClassesService } from '../service/sisyphus-classes.service';

@Injectable({ providedIn: 'root' })
export class SisyphusClassesRoutingResolveService implements Resolve<ISisyphusClasses> {
  constructor(protected service: SisyphusClassesService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISisyphusClasses> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sisyphusClasses: HttpResponse<SisyphusClasses>) => {
          if (sisyphusClasses.body) {
            return of(sisyphusClasses.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SisyphusClasses());
  }
}
