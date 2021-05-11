import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SisyphusClientComponent } from '../list/sisyphus-client.component';
import { SisyphusClientDetailComponent } from '../detail/sisyphus-client-detail.component';
import { SisyphusClientUpdateComponent } from '../update/sisyphus-client-update.component';
import { SisyphusClientRoutingResolveService } from './sisyphus-client-routing-resolve.service';

const sisyphusClientRoute: Routes = [
  {
    path: '',
    component: SisyphusClientComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SisyphusClientDetailComponent,
    resolve: {
      sisyphusClient: SisyphusClientRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SisyphusClientUpdateComponent,
    resolve: {
      sisyphusClient: SisyphusClientRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SisyphusClientUpdateComponent,
    resolve: {
      sisyphusClient: SisyphusClientRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sisyphusClientRoute)],
  exports: [RouterModule],
})
export class SisyphusClientRoutingModule {}
