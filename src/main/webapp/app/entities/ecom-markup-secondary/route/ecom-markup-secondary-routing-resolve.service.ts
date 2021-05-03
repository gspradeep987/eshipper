import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEcomMarkupSecondary, EcomMarkupSecondary } from '../ecom-markup-secondary.model';
import { EcomMarkupSecondaryService } from '../service/ecom-markup-secondary.service';

@Injectable({ providedIn: 'root' })
export class EcomMarkupSecondaryRoutingResolveService implements Resolve<IEcomMarkupSecondary> {
  constructor(protected service: EcomMarkupSecondaryService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomMarkupSecondary> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ecomMarkupSecondary: HttpResponse<EcomMarkupSecondary>) => {
          if (ecomMarkupSecondary.body) {
            return of(ecomMarkupSecondary.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomMarkupSecondary());
  }
}
