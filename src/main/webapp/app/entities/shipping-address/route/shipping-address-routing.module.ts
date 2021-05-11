import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ShippingAddressComponent } from '../list/shipping-address.component';
import { ShippingAddressDetailComponent } from '../detail/shipping-address-detail.component';
import { ShippingAddressUpdateComponent } from '../update/shipping-address-update.component';
import { ShippingAddressRoutingResolveService } from './shipping-address-routing-resolve.service';

const shippingAddressRoute: Routes = [
  {
    path: '',
    component: ShippingAddressComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ShippingAddressDetailComponent,
    resolve: {
      shippingAddress: ShippingAddressRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ShippingAddressUpdateComponent,
    resolve: {
      shippingAddress: ShippingAddressRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ShippingAddressUpdateComponent,
    resolve: {
      shippingAddress: ShippingAddressRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(shippingAddressRoute)],
  exports: [RouterModule],
})
export class ShippingAddressRoutingModule {}
