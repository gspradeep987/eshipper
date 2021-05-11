import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EcomStoreSyncComponent } from '../list/ecom-store-sync.component';
import { EcomStoreSyncDetailComponent } from '../detail/ecom-store-sync-detail.component';
import { EcomStoreSyncUpdateComponent } from '../update/ecom-store-sync-update.component';
import { EcomStoreSyncRoutingResolveService } from './ecom-store-sync-routing-resolve.service';

const ecomStoreSyncRoute: Routes = [
  {
    path: '',
    component: EcomStoreSyncComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomStoreSyncDetailComponent,
    resolve: {
      ecomStoreSync: EcomStoreSyncRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomStoreSyncUpdateComponent,
    resolve: {
      ecomStoreSync: EcomStoreSyncRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomStoreSyncUpdateComponent,
    resolve: {
      ecomStoreSync: EcomStoreSyncRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ecomStoreSyncRoute)],
  exports: [RouterModule],
})
export class EcomStoreSyncRoutingModule {}
