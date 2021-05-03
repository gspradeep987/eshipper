import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EcomProductComponent } from '../list/ecom-product.component';
import { EcomProductDetailComponent } from '../detail/ecom-product-detail.component';
import { EcomProductUpdateComponent } from '../update/ecom-product-update.component';
import { EcomProductRoutingResolveService } from './ecom-product-routing-resolve.service';

const ecomProductRoute: Routes = [
  {
    path: '',
    component: EcomProductComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomProductDetailComponent,
    resolve: {
      ecomProduct: EcomProductRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomProductUpdateComponent,
    resolve: {
      ecomProduct: EcomProductRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomProductUpdateComponent,
    resolve: {
      ecomProduct: EcomProductRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ecomProductRoute)],
  exports: [RouterModule],
})
export class EcomProductRoutingModule {}
