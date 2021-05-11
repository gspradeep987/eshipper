import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EcomStoreMarkupComponent } from '../list/ecom-store-markup.component';
import { EcomStoreMarkupDetailComponent } from '../detail/ecom-store-markup-detail.component';
import { EcomStoreMarkupUpdateComponent } from '../update/ecom-store-markup-update.component';
import { EcomStoreMarkupRoutingResolveService } from './ecom-store-markup-routing-resolve.service';

const ecomStoreMarkupRoute: Routes = [
  {
    path: '',
    component: EcomStoreMarkupComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomStoreMarkupDetailComponent,
    resolve: {
      ecomStoreMarkup: EcomStoreMarkupRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomStoreMarkupUpdateComponent,
    resolve: {
      ecomStoreMarkup: EcomStoreMarkupRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomStoreMarkupUpdateComponent,
    resolve: {
      ecomStoreMarkup: EcomStoreMarkupRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ecomStoreMarkupRoute)],
  exports: [RouterModule],
})
export class EcomStoreMarkupRoutingModule {}
