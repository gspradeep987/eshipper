import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWoSalesCommissionCarrier, WoSalesCommissionCarrier } from 'app/shared/model/wo-sales-commission-carrier.model';
import { WoSalesCommissionCarrierService } from './wo-sales-commission-carrier.service';
import { WoSalesCommissionCarrierComponent } from './wo-sales-commission-carrier.component';
import { WoSalesCommissionCarrierDetailComponent } from './wo-sales-commission-carrier-detail.component';
import { WoSalesCommissionCarrierUpdateComponent } from './wo-sales-commission-carrier-update.component';

@Injectable({ providedIn: 'root' })
export class WoSalesCommissionCarrierResolve implements Resolve<IWoSalesCommissionCarrier> {
  constructor(private service: WoSalesCommissionCarrierService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWoSalesCommissionCarrier> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((woSalesCommissionCarrier: HttpResponse<WoSalesCommissionCarrier>) => {
          if (woSalesCommissionCarrier.body) {
            return of(woSalesCommissionCarrier.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WoSalesCommissionCarrier());
  }
}

export const woSalesCommissionCarrierRoute: Routes = [
  {
    path: '',
    component: WoSalesCommissionCarrierComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.woSalesCommissionCarrier.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: WoSalesCommissionCarrierDetailComponent,
    resolve: {
      woSalesCommissionCarrier: WoSalesCommissionCarrierResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesCommissionCarrier.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: WoSalesCommissionCarrierUpdateComponent,
    resolve: {
      woSalesCommissionCarrier: WoSalesCommissionCarrierResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesCommissionCarrier.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: WoSalesCommissionCarrierUpdateComponent,
    resolve: {
      woSalesCommissionCarrier: WoSalesCommissionCarrierResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesCommissionCarrier.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
