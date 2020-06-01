import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IElasticStatus, ElasticStatus } from 'app/shared/model/elastic-status.model';
import { ElasticStatusService } from './elastic-status.service';
import { ElasticStatusComponent } from './elastic-status.component';
import { ElasticStatusDetailComponent } from './elastic-status-detail.component';
import { ElasticStatusUpdateComponent } from './elastic-status-update.component';

@Injectable({ providedIn: 'root' })
export class ElasticStatusResolve implements Resolve<IElasticStatus> {
  constructor(private service: ElasticStatusService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IElasticStatus> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((elasticStatus: HttpResponse<ElasticStatus>) => {
          if (elasticStatus.body) {
            return of(elasticStatus.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ElasticStatus());
  }
}

export const elasticStatusRoute: Routes = [
  {
    path: '',
    component: ElasticStatusComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.elasticStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ElasticStatusDetailComponent,
    resolve: {
      elasticStatus: ElasticStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.elasticStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ElasticStatusUpdateComponent,
    resolve: {
      elasticStatus: ElasticStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.elasticStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ElasticStatusUpdateComponent,
    resolve: {
      elasticStatus: ElasticStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.elasticStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
