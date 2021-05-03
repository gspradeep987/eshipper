import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEcomMarkupPrimary, EcomMarkupPrimary } from '../ecom-markup-primary.model';
import { EcomMarkupPrimaryService } from '../service/ecom-markup-primary.service';

@Injectable({ providedIn: 'root' })
export class EcomMarkupPrimaryRoutingResolveService implements Resolve<IEcomMarkupPrimary> {
  constructor(protected service: EcomMarkupPrimaryService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomMarkupPrimary> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ecomMarkupPrimary: HttpResponse<EcomMarkupPrimary>) => {
          if (ecomMarkupPrimary.body) {
            return of(ecomMarkupPrimary.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomMarkupPrimary());
  }
}
