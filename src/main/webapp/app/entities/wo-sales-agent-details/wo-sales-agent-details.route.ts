import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWoSalesAgentDetails, WoSalesAgentDetails } from 'app/shared/model/wo-sales-agent-details.model';
import { WoSalesAgentDetailsService } from './wo-sales-agent-details.service';
import { WoSalesAgentDetailsComponent } from './wo-sales-agent-details.component';
import { WoSalesAgentDetailsDetailComponent } from './wo-sales-agent-details-detail.component';
import { WoSalesAgentDetailsUpdateComponent } from './wo-sales-agent-details-update.component';

@Injectable({ providedIn: 'root' })
export class WoSalesAgentDetailsResolve implements Resolve<IWoSalesAgentDetails> {
  constructor(private service: WoSalesAgentDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWoSalesAgentDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((woSalesAgentDetails: HttpResponse<WoSalesAgentDetails>) => {
          if (woSalesAgentDetails.body) {
            return of(woSalesAgentDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WoSalesAgentDetails());
  }
}

export const woSalesAgentDetailsRoute: Routes = [
  {
    path: '',
    component: WoSalesAgentDetailsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.woSalesAgentDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: WoSalesAgentDetailsDetailComponent,
    resolve: {
      woSalesAgentDetails: WoSalesAgentDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesAgentDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: WoSalesAgentDetailsUpdateComponent,
    resolve: {
      woSalesAgentDetails: WoSalesAgentDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesAgentDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: WoSalesAgentDetailsUpdateComponent,
    resolve: {
      woSalesAgentDetails: WoSalesAgentDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesAgentDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
