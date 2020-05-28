import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWoSalesCommissionTransfer, WoSalesCommissionTransfer } from 'app/shared/model/wo-sales-commission-transfer.model';
import { WoSalesCommissionTransferService } from './wo-sales-commission-transfer.service';
import { WoSalesCommissionTransferComponent } from './wo-sales-commission-transfer.component';
import { WoSalesCommissionTransferDetailComponent } from './wo-sales-commission-transfer-detail.component';
import { WoSalesCommissionTransferUpdateComponent } from './wo-sales-commission-transfer-update.component';

@Injectable({ providedIn: 'root' })
export class WoSalesCommissionTransferResolve implements Resolve<IWoSalesCommissionTransfer> {
  constructor(private service: WoSalesCommissionTransferService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWoSalesCommissionTransfer> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((woSalesCommissionTransfer: HttpResponse<WoSalesCommissionTransfer>) => {
          if (woSalesCommissionTransfer.body) {
            return of(woSalesCommissionTransfer.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WoSalesCommissionTransfer());
  }
}

export const woSalesCommissionTransferRoute: Routes = [
  {
    path: '',
    component: WoSalesCommissionTransferComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.woSalesCommissionTransfer.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: WoSalesCommissionTransferDetailComponent,
    resolve: {
      woSalesCommissionTransfer: WoSalesCommissionTransferResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesCommissionTransfer.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: WoSalesCommissionTransferUpdateComponent,
    resolve: {
      woSalesCommissionTransfer: WoSalesCommissionTransferResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesCommissionTransfer.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: WoSalesCommissionTransferUpdateComponent,
    resolve: {
      woSalesCommissionTransfer: WoSalesCommissionTransferResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woSalesCommissionTransfer.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
