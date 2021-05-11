import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { EcomStoreShipmentSettingsComponent } from './list/ecom-store-shipment-settings.component';
import { EcomStoreShipmentSettingsDetailComponent } from './detail/ecom-store-shipment-settings-detail.component';
import { EcomStoreShipmentSettingsUpdateComponent } from './update/ecom-store-shipment-settings-update.component';
import { EcomStoreShipmentSettingsDeleteDialogComponent } from './delete/ecom-store-shipment-settings-delete-dialog.component';
import { EcomStoreShipmentSettingsRoutingModule } from './route/ecom-store-shipment-settings-routing.module';

@NgModule({
  imports: [SharedModule, EcomStoreShipmentSettingsRoutingModule],
  declarations: [
    EcomStoreShipmentSettingsComponent,
    EcomStoreShipmentSettingsDetailComponent,
    EcomStoreShipmentSettingsUpdateComponent,
    EcomStoreShipmentSettingsDeleteDialogComponent,
  ],
  entryComponents: [EcomStoreShipmentSettingsDeleteDialogComponent],
})
export class EcomStoreShipmentSettingsModule {}
