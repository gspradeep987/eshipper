import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomOrderPaymentStatus, EcomOrderPaymentStatus } from 'app/shared/model/ecom-order-payment-status.model';
import { EcomOrderPaymentStatusService } from './ecom-order-payment-status.service';
import { EcomOrderPaymentStatusComponent } from './ecom-order-payment-status.component';
import { EcomOrderPaymentStatusDetailComponent } from './ecom-order-payment-status-detail.component';
import { EcomOrderPaymentStatusUpdateComponent } from './ecom-order-payment-status-update.component';

@Injectable({ providedIn: 'root' })
export class EcomOrderPaymentStatusResolve implements Resolve<IEcomOrderPaymentStatus> {
  constructor(private service: EcomOrderPaymentStatusService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomOrderPaymentStatus> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomOrderPaymentStatus: HttpResponse<EcomOrderPaymentStatus>) => {
          if (ecomOrderPaymentStatus.body) {
            return of(ecomOrderPaymentStatus.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomOrderPaymentStatus());
  }
}

export const ecomOrderPaymentStatusRoute: Routes = [
  {
    path: '',
    component: EcomOrderPaymentStatusComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomOrderPaymentStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomOrderPaymentStatusDetailComponent,
    resolve: {
      ecomOrderPaymentStatus: EcomOrderPaymentStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomOrderPaymentStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomOrderPaymentStatusUpdateComponent,
    resolve: {
      ecomOrderPaymentStatus: EcomOrderPaymentStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomOrderPaymentStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomOrderPaymentStatusUpdateComponent,
    resolve: {
      ecomOrderPaymentStatus: EcomOrderPaymentStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomOrderPaymentStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
