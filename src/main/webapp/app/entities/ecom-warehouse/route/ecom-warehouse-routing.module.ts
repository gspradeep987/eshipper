import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EcomWarehouseComponent } from '../list/ecom-warehouse.component';
import { EcomWarehouseDetailComponent } from '../detail/ecom-warehouse-detail.component';
import { EcomWarehouseUpdateComponent } from '../update/ecom-warehouse-update.component';
import { EcomWarehouseRoutingResolveService } from './ecom-warehouse-routing-resolve.service';

const ecomWarehouseRoute: Routes = [
  {
    path: '',
    component: EcomWarehouseComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomWarehouseDetailComponent,
    resolve: {
      ecomWarehouse: EcomWarehouseRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomWarehouseUpdateComponent,
    resolve: {
      ecomWarehouse: EcomWarehouseRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomWarehouseUpdateComponent,
    resolve: {
      ecomWarehouse: EcomWarehouseRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ecomWarehouseRoute)],
  exports: [RouterModule],
})
export class EcomWarehouseRoutingModule {}
