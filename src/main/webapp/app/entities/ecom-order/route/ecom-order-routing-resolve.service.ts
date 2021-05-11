import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEcomOrder, EcomOrder } from '../ecom-order.model';
import { EcomOrderService } from '../service/ecom-order.service';

@Injectable({ providedIn: 'root' })
export class EcomOrderRoutingResolveService implements Resolve<IEcomOrder> {
  constructor(protected service: EcomOrderService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomOrder> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ecomOrder: HttpResponse<EcomOrder>) => {
          if (ecomOrder.body) {
            return of(ecomOrder.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomOrder());
  }
}
