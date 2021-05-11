import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EcomOrderComponent } from '../list/ecom-order.component';
import { EcomOrderDetailComponent } from '../detail/ecom-order-detail.component';
import { EcomOrderUpdateComponent } from '../update/ecom-order-update.component';
import { EcomOrderRoutingResolveService } from './ecom-order-routing-resolve.service';

const ecomOrderRoute: Routes = [
  {
    path: '',
    component: EcomOrderComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomOrderDetailComponent,
    resolve: {
      ecomOrder: EcomOrderRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomOrderUpdateComponent,
    resolve: {
      ecomOrder: EcomOrderRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomOrderUpdateComponent,
    resolve: {
      ecomOrder: EcomOrderRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ecomOrderRoute)],
  exports: [RouterModule],
})
export class EcomOrderRoutingModule {}
