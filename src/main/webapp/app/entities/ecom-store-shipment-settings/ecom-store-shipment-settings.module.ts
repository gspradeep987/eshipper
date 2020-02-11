import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomStoreShipmentSettingsComponent } from './ecom-store-shipment-settings.component';
import { EcomStoreShipmentSettingsDetailComponent } from './ecom-store-shipment-settings-detail.component';
import { EcomStoreShipmentSettingsUpdateComponent } from './ecom-store-shipment-settings-update.component';
import { EcomStoreShipmentSettingsDeleteDialogComponent } from './ecom-store-shipment-settings-delete-dialog.component';
import { ecomStoreShipmentSettingsRoute } from './ecom-store-shipment-settings.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomStoreShipmentSettingsRoute)],
  declarations: [
    EcomStoreShipmentSettingsComponent,
    EcomStoreShipmentSettingsDetailComponent,
    EcomStoreShipmentSettingsUpdateComponent,
    EcomStoreShipmentSettingsDeleteDialogComponent
  ],
  entryComponents: [EcomStoreShipmentSettingsDeleteDialogComponent]
})
export class EshipperEcomStoreShipmentSettingsModule {}
