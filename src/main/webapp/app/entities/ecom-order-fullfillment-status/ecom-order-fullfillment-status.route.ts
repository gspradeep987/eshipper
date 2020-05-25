import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomOrderFullfillmentStatus, EcomOrderFullfillmentStatus } from 'app/shared/model/ecom-order-fullfillment-status.model';
import { EcomOrderFullfillmentStatusService } from './ecom-order-fullfillment-status.service';
import { EcomOrderFullfillmentStatusComponent } from './ecom-order-fullfillment-status.component';
import { EcomOrderFullfillmentStatusDetailComponent } from './ecom-order-fullfillment-status-detail.component';
import { EcomOrderFullfillmentStatusUpdateComponent } from './ecom-order-fullfillment-status-update.component';

@Injectable({ providedIn: 'root' })
export class EcomOrderFullfillmentStatusResolve implements Resolve<IEcomOrderFullfillmentStatus> {
  constructor(private service: EcomOrderFullfillmentStatusService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomOrderFullfillmentStatus> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomOrderFullfillmentStatus: HttpResponse<EcomOrderFullfillmentStatus>) => {
          if (ecomOrderFullfillmentStatus.body) {
            return of(ecomOrderFullfillmentStatus.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomOrderFullfillmentStatus());
  }
}

export const ecomOrderFullfillmentStatusRoute: Routes = [
  {
    path: '',
    component: EcomOrderFullfillmentStatusComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomOrderFullfillmentStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomOrderFullfillmentStatusDetailComponent,
    resolve: {
      ecomOrderFullfillmentStatus: EcomOrderFullfillmentStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomOrderFullfillmentStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomOrderFullfillmentStatusUpdateComponent,
    resolve: {
      ecomOrderFullfillmentStatus: EcomOrderFullfillmentStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomOrderFullfillmentStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomOrderFullfillmentStatusUpdateComponent,
    resolve: {
      ecomOrderFullfillmentStatus: EcomOrderFullfillmentStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomOrderFullfillmentStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
