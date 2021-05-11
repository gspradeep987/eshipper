import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { ShipmentServiceComponent } from './list/shipment-service.component';
import { ShipmentServiceDetailComponent } from './detail/shipment-service-detail.component';
import { ShipmentServiceUpdateComponent } from './update/shipment-service-update.component';
import { ShipmentServiceDeleteDialogComponent } from './delete/shipment-service-delete-dialog.component';
import { ShipmentServiceRoutingModule } from './route/shipment-service-routing.module';

@NgModule({
  imports: [SharedModule, ShipmentServiceRoutingModule],
  declarations: [
    ShipmentServiceComponent,
    ShipmentServiceDetailComponent,
    ShipmentServiceUpdateComponent,
    ShipmentServiceDeleteDialogComponent,
  ],
  entryComponents: [ShipmentServiceDeleteDialogComponent],
})
export class ShipmentServiceModule {}
