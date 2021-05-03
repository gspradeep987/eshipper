import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EcomProductImageComponent } from '../list/ecom-product-image.component';
import { EcomProductImageDetailComponent } from '../detail/ecom-product-image-detail.component';
import { EcomProductImageUpdateComponent } from '../update/ecom-product-image-update.component';
import { EcomProductImageRoutingResolveService } from './ecom-product-image-routing-resolve.service';

const ecomProductImageRoute: Routes = [
  {
    path: '',
    component: EcomProductImageComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomProductImageDetailComponent,
    resolve: {
      ecomProductImage: EcomProductImageRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomProductImageUpdateComponent,
    resolve: {
      ecomProductImage: EcomProductImageRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomProductImageUpdateComponent,
    resolve: {
      ecomProductImage: EcomProductImageRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ecomProductImageRoute)],
  exports: [RouterModule],
})
export class EcomProductImageRoutingModule {}
