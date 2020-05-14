import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWoSalesAgent, WoSalesAgent } from 'app/shared/model/wo-sales-agent.model';
import { WoSalesAgentService } from './wo-sales-agent.service';
import { WoSalesAgentComponent } from './wo-sales-agent.component';
import { WoSalesAgentDetailComponent } from './wo-sales-agent-detail.component';
import { WoSalesAgentUpdateComponent } from './wo-sales-agent-update.component';

@Injectable({ providedIn: 'root' })
export class WoSalesAgentResolve implements Resolve<IWoSalesAgent> {
  constructor(private service: WoSalesAgentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWoSalesAgent> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((woSalesAgent: HttpResponse<WoSalesAgent>) => {
          if (woSalesAgent.body) {
            return of(woSalesAgent.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WoSalesAgent());
  }
}

export const woSalesAgentRoute: Routes = [
  {
    path: '',
    component: WoSalesAgentComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.woSalesAgent.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WoSalesAgentDetailComponent,
    resolve: {
      woSalesAgent: WoSalesAgentResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesAgent.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WoSalesAgentUpdateComponent,
    resolve: {
      woSalesAgent: WoSalesAgentResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesAgent.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WoSalesAgentUpdateComponent,
    resolve: {
      woSalesAgent: WoSalesAgentResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesAgent.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
