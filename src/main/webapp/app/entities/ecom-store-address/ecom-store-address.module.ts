import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomStoreAddressComponent } from './ecom-store-address.component';
import { EcomStoreAddressDetailComponent } from './ecom-store-address-detail.component';
import { EcomStoreAddressUpdateComponent } from './ecom-store-address-update.component';
import { EcomStoreAddressDeleteDialogComponent } from './ecom-store-address-delete-dialog.component';
import { ecomStoreAddressRoute } from './ecom-store-address.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomStoreAddressRoute)],
  declarations: [
    EcomStoreAddressComponent,
    EcomStoreAddressDetailComponent,
    EcomStoreAddressUpdateComponent,
    EcomStoreAddressDeleteDialogComponent
  ],
  entryComponents: [EcomStoreAddressDeleteDialogComponent]
})
export class EshipperEcomStoreAddressModule {}
