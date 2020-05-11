import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWoSalesOperationalCarrier, WoSalesOperationalCarrier } from 'app/shared/model/wo-sales-operational-carrier.model';
import { WoSalesOperationalCarrierService } from './wo-sales-operational-carrier.service';
import { WoSalesOperationalCarrierComponent } from './wo-sales-operational-carrier.component';
import { WoSalesOperationalCarrierDetailComponent } from './wo-sales-operational-carrier-detail.component';
import { WoSalesOperationalCarrierUpdateComponent } from './wo-sales-operational-carrier-update.component';

@Injectable({ providedIn: 'root' })
export class WoSalesOperationalCarrierResolve implements Resolve<IWoSalesOperationalCarrier> {
  constructor(private service: WoSalesOperationalCarrierService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWoSalesOperationalCarrier> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((woSalesOperationalCarrier: HttpResponse<WoSalesOperationalCarrier>) => {
          if (woSalesOperationalCarrier.body) {
            return of(woSalesOperationalCarrier.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WoSalesOperationalCarrier());
  }
}

export const woSalesOperationalCarrierRoute: Routes = [
  {
    path: '',
    component: WoSalesOperationalCarrierComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.woSalesOperationalCarrier.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WoSalesOperationalCarrierDetailComponent,
    resolve: {
      woSalesOperationalCarrier: WoSalesOperationalCarrierResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesOperationalCarrier.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WoSalesOperationalCarrierUpdateComponent,
    resolve: {
      woSalesOperationalCarrier: WoSalesOperationalCarrierResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesOperationalCarrier.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WoSalesOperationalCarrierUpdateComponent,
    resolve: {
      woSalesOperationalCarrier: WoSalesOperationalCarrierResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesOperationalCarrier.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
