import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWoSalesCommissionDetails, WoSalesCommissionDetails } from 'app/shared/model/wo-sales-commission-details.model';
import { WoSalesCommissionDetailsService } from './wo-sales-commission-details.service';
import { WoSalesCommissionDetailsComponent } from './wo-sales-commission-details.component';
import { WoSalesCommissionDetailsDetailComponent } from './wo-sales-commission-details-detail.component';
import { WoSalesCommissionDetailsUpdateComponent } from './wo-sales-commission-details-update.component';

@Injectable({ providedIn: 'root' })
export class WoSalesCommissionDetailsResolve implements Resolve<IWoSalesCommissionDetails> {
  constructor(private service: WoSalesCommissionDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWoSalesCommissionDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((woSalesCommissionDetails: HttpResponse<WoSalesCommissionDetails>) => {
          if (woSalesCommissionDetails.body) {
            return of(woSalesCommissionDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WoSalesCommissionDetails());
  }
}

export const woSalesCommissionDetailsRoute: Routes = [
  {
    path: '',
    component: WoSalesCommissionDetailsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.woSalesCommissionDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WoSalesCommissionDetailsDetailComponent,
    resolve: {
      woSalesCommissionDetails: WoSalesCommissionDetailsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesCommissionDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WoSalesCommissionDetailsUpdateComponent,
    resolve: {
      woSalesCommissionDetails: WoSalesCommissionDetailsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesCommissionDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WoSalesCommissionDetailsUpdateComponent,
    resolve: {
      woSalesCommissionDetails: WoSalesCommissionDetailsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesCommissionDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
