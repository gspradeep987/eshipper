import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EcomStoreComponent } from '../list/ecom-store.component';
import { EcomStoreDetailComponent } from '../detail/ecom-store-detail.component';
import { EcomStoreUpdateComponent } from '../update/ecom-store-update.component';
import { EcomStoreRoutingResolveService } from './ecom-store-routing-resolve.service';

const ecomStoreRoute: Routes = [
  {
    path: '',
    component: EcomStoreComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomStoreDetailComponent,
    resolve: {
      ecomStore: EcomStoreRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomStoreUpdateComponent,
    resolve: {
      ecomStore: EcomStoreRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomStoreUpdateComponent,
    resolve: {
      ecomStore: EcomStoreRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ecomStoreRoute)],
  exports: [RouterModule],
})
export class EcomStoreRoutingModule {}
