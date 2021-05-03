import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EcomStoreAddressComponent } from '../list/ecom-store-address.component';
import { EcomStoreAddressDetailComponent } from '../detail/ecom-store-address-detail.component';
import { EcomStoreAddressUpdateComponent } from '../update/ecom-store-address-update.component';
import { EcomStoreAddressRoutingResolveService } from './ecom-store-address-routing-resolve.service';

const ecomStoreAddressRoute: Routes = [
  {
    path: '',
    component: EcomStoreAddressComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomStoreAddressDetailComponent,
    resolve: {
      ecomStoreAddress: EcomStoreAddressRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomStoreAddressUpdateComponent,
    resolve: {
      ecomStoreAddress: EcomStoreAddressRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomStoreAddressUpdateComponent,
    resolve: {
      ecomStoreAddress: EcomStoreAddressRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ecomStoreAddressRoute)],
  exports: [RouterModule],
})
export class EcomStoreAddressRoutingModule {}
