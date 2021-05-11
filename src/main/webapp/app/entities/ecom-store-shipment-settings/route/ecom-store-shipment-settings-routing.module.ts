import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EcomStoreShipmentSettingsComponent } from '../list/ecom-store-shipment-settings.component';
import { EcomStoreShipmentSettingsDetailComponent } from '../detail/ecom-store-shipment-settings-detail.component';
import { EcomStoreShipmentSettingsUpdateComponent } from '../update/ecom-store-shipment-settings-update.component';
import { EcomStoreShipmentSettingsRoutingResolveService } from './ecom-store-shipment-settings-routing-resolve.service';

const ecomStoreShipmentSettingsRoute: Routes = [
  {
    path: '',
    component: EcomStoreShipmentSettingsComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomStoreShipmentSettingsDetailComponent,
    resolve: {
      ecomStoreShipmentSettings: EcomStoreShipmentSettingsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomStoreShipmentSettingsUpdateComponent,
    resolve: {
      ecomStoreShipmentSettings: EcomStoreShipmentSettingsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomStoreShipmentSettingsUpdateComponent,
    resolve: {
      ecomStoreShipmentSettings: EcomStoreShipmentSettingsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ecomStoreShipmentSettingsRoute)],
  exports: [RouterModule],
})
export class EcomStoreShipmentSettingsRoutingModule {}
