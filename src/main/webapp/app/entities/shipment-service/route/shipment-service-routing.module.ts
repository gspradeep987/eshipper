import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ShipmentServiceComponent } from '../list/shipment-service.component';
import { ShipmentServiceDetailComponent } from '../detail/shipment-service-detail.component';
import { ShipmentServiceUpdateComponent } from '../update/shipment-service-update.component';
import { ShipmentServiceRoutingResolveService } from './shipment-service-routing-resolve.service';

const shipmentServiceRoute: Routes = [
  {
    path: '',
    component: ShipmentServiceComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ShipmentServiceDetailComponent,
    resolve: {
      shipmentService: ShipmentServiceRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ShipmentServiceUpdateComponent,
    resolve: {
      shipmentService: ShipmentServiceRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ShipmentServiceUpdateComponent,
    resolve: {
      shipmentService: ShipmentServiceRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(shipmentServiceRoute)],
  exports: [RouterModule],
})
export class ShipmentServiceRoutingModule {}
