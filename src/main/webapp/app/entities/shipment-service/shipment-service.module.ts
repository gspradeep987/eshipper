import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ShipmentServiceComponent } from './shipment-service.component';
import { ShipmentServiceDetailComponent } from './shipment-service-detail.component';
import { ShipmentServiceUpdateComponent } from './shipment-service-update.component';
import { ShipmentServiceDeleteDialogComponent } from './shipment-service-delete-dialog.component';
import { shipmentServiceRoute } from './shipment-service.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(shipmentServiceRoute)],
  declarations: [
    ShipmentServiceComponent,
    ShipmentServiceDetailComponent,
    ShipmentServiceUpdateComponent,
    ShipmentServiceDeleteDialogComponent
  ],
  entryComponents: [ShipmentServiceDeleteDialogComponent]
})
export class EshipperShipmentServiceModule {}
