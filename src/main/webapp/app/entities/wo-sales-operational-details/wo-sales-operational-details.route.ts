import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWoSalesOperationalDetails, WoSalesOperationalDetails } from 'app/shared/model/wo-sales-operational-details.model';
import { WoSalesOperationalDetailsService } from './wo-sales-operational-details.service';
import { WoSalesOperationalDetailsComponent } from './wo-sales-operational-details.component';
import { WoSalesOperationalDetailsDetailComponent } from './wo-sales-operational-details-detail.component';
import { WoSalesOperationalDetailsUpdateComponent } from './wo-sales-operational-details-update.component';

@Injectable({ providedIn: 'root' })
export class WoSalesOperationalDetailsResolve implements Resolve<IWoSalesOperationalDetails> {
  constructor(private service: WoSalesOperationalDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWoSalesOperationalDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((woSalesOperationalDetails: HttpResponse<WoSalesOperationalDetails>) => {
          if (woSalesOperationalDetails.body) {
            return of(woSalesOperationalDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WoSalesOperationalDetails());
  }
}

export const woSalesOperationalDetailsRoute: Routes = [
  {
    path: '',
    component: WoSalesOperationalDetailsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.woSalesOperationalDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: WoSalesOperationalDetailsDetailComponent,
    resolve: {
      woSalesOperationalDetails: WoSalesOperationalDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesOperationalDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: WoSalesOperationalDetailsUpdateComponent,
    resolve: {
      woSalesOperationalDetails: WoSalesOperationalDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesOperationalDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: WoSalesOperationalDetailsUpdateComponent,
    resolve: {
      woSalesOperationalDetails: WoSalesOperationalDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesOperationalDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
