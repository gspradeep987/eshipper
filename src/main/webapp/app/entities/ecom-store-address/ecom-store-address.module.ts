import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { EcomStoreAddressComponent } from './list/ecom-store-address.component';
import { EcomStoreAddressDetailComponent } from './detail/ecom-store-address-detail.component';
import { EcomStoreAddressUpdateComponent } from './update/ecom-store-address-update.component';
import { EcomStoreAddressDeleteDialogComponent } from './delete/ecom-store-address-delete-dialog.component';
import { EcomStoreAddressRoutingModule } from './route/ecom-store-address-routing.module';

@NgModule({
  imports: [SharedModule, EcomStoreAddressRoutingModule],
  declarations: [
    EcomStoreAddressComponent,
    EcomStoreAddressDetailComponent,
    EcomStoreAddressUpdateComponent,
    EcomStoreAddressDeleteDialogComponent,
  ],
  entryComponents: [EcomStoreAddressDeleteDialogComponent],
})
export class EcomStoreAddressModule {}
