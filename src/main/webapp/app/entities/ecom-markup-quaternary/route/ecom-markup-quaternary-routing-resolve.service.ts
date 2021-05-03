import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEcomMarkupQuaternary, EcomMarkupQuaternary } from '../ecom-markup-quaternary.model';
import { EcomMarkupQuaternaryService } from '../service/ecom-markup-quaternary.service';

@Injectable({ providedIn: 'root' })
export class EcomMarkupQuaternaryRoutingResolveService implements Resolve<IEcomMarkupQuaternary> {
  constructor(protected service: EcomMarkupQuaternaryService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomMarkupQuaternary> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ecomMarkupQuaternary: HttpResponse<EcomMarkupQuaternary>) => {
          if (ecomMarkupQuaternary.body) {
            return of(ecomMarkupQuaternary.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomMarkupQuaternary());
  }
}
