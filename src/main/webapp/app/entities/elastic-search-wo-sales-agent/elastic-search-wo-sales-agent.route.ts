import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IElasticSearchWoSalesAgent, ElasticSearchWoSalesAgent } from 'app/shared/model/elastic-search-wo-sales-agent.model';
import { ElasticSearchWoSalesAgentService } from './elastic-search-wo-sales-agent.service';
import { ElasticSearchWoSalesAgentComponent } from './elastic-search-wo-sales-agent.component';
import { ElasticSearchWoSalesAgentDetailComponent } from './elastic-search-wo-sales-agent-detail.component';
import { ElasticSearchWoSalesAgentUpdateComponent } from './elastic-search-wo-sales-agent-update.component';

@Injectable({ providedIn: 'root' })
export class ElasticSearchWoSalesAgentResolve implements Resolve<IElasticSearchWoSalesAgent> {
  constructor(private service: ElasticSearchWoSalesAgentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IElasticSearchWoSalesAgent> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((elasticSearchWoSalesAgent: HttpResponse<ElasticSearchWoSalesAgent>) => {
          if (elasticSearchWoSalesAgent.body) {
            return of(elasticSearchWoSalesAgent.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ElasticSearchWoSalesAgent());
  }
}

export const elasticSearchWoSalesAgentRoute: Routes = [
  {
    path: '',
    component: ElasticSearchWoSalesAgentComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.elasticSearchWoSalesAgent.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ElasticSearchWoSalesAgentDetailComponent,
    resolve: {
      elasticSearchWoSalesAgent: ElasticSearchWoSalesAgentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.elasticSearchWoSalesAgent.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ElasticSearchWoSalesAgentUpdateComponent,
    resolve: {
      elasticSearchWoSalesAgent: ElasticSearchWoSalesAgentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.elasticSearchWoSalesAgent.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ElasticSearchWoSalesAgentUpdateComponent,
    resolve: {
      elasticSearchWoSalesAgent: ElasticSearchWoSalesAgentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.elasticSearchWoSalesAgent.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
