import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEcomStoreMarkup, EcomStoreMarkup } from '../ecom-store-markup.model';
import { EcomStoreMarkupService } from '../service/ecom-store-markup.service';

@Injectable({ providedIn: 'root' })
export class EcomStoreMarkupRoutingResolveService implements Resolve<IEcomStoreMarkup> {
  constructor(protected service: EcomStoreMarkupService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomStoreMarkup> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ecomStoreMarkup: HttpResponse<EcomStoreMarkup>) => {
          if (ecomStoreMarkup.body) {
            return of(ecomStoreMarkup.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomStoreMarkup());
  }
}
