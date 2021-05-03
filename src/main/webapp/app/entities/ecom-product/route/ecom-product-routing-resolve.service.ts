import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEcomProduct, EcomProduct } from '../ecom-product.model';
import { EcomProductService } from '../service/ecom-product.service';

@Injectable({ providedIn: 'root' })
export class EcomProductRoutingResolveService implements Resolve<IEcomProduct> {
  constructor(protected service: EcomProductService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomProduct> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ecomProduct: HttpResponse<EcomProduct>) => {
          if (ecomProduct.body) {
            return of(ecomProduct.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomProduct());
  }
}
