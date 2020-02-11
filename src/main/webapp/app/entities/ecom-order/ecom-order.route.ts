import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomOrder, EcomOrder } from 'app/shared/model/ecom-order.model';
import { EcomOrderService } from './ecom-order.service';
import { EcomOrderComponent } from './ecom-order.component';
import { EcomOrderDetailComponent } from './ecom-order-detail.component';
import { EcomOrderUpdateComponent } from './ecom-order-update.component';

@Injectable({ providedIn: 'root' })
export class EcomOrderResolve implements Resolve<IEcomOrder> {
  constructor(private service: EcomOrderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomOrder> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomOrder: HttpResponse<EcomOrder>) => {
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

export const ecomOrderRoute: Routes = [
  {
    path: '',
    component: EcomOrderComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EcomOrderDetailComponent,
    resolve: {
      ecomOrder: EcomOrderResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EcomOrderUpdateComponent,
    resolve: {
      ecomOrder: EcomOrderResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EcomOrderUpdateComponent,
    resolve: {
      ecomOrder: EcomOrderResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
