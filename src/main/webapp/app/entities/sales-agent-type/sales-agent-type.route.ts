import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISalesAgentType, SalesAgentType } from 'app/shared/model/sales-agent-type.model';
import { SalesAgentTypeService } from './sales-agent-type.service';
import { SalesAgentTypeComponent } from './sales-agent-type.component';
import { SalesAgentTypeDetailComponent } from './sales-agent-type-detail.component';
import { SalesAgentTypeUpdateComponent } from './sales-agent-type-update.component';

@Injectable({ providedIn: 'root' })
export class SalesAgentTypeResolve implements Resolve<ISalesAgentType> {
  constructor(private service: SalesAgentTypeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISalesAgentType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((salesAgentType: HttpResponse<SalesAgentType>) => {
          if (salesAgentType.body) {
            return of(salesAgentType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SalesAgentType());
  }
}

export const salesAgentTypeRoute: Routes = [
  {
    path: '',
    component: SalesAgentTypeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.salesAgentType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SalesAgentTypeDetailComponent,
    resolve: {
      salesAgentType: SalesAgentTypeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.salesAgentType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SalesAgentTypeUpdateComponent,
    resolve: {
      salesAgentType: SalesAgentTypeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.salesAgentType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SalesAgentTypeUpdateComponent,
    resolve: {
      salesAgentType: SalesAgentTypeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.salesAgentType.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
