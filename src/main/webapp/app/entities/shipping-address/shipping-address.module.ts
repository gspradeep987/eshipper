import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { ShippingAddressComponent } from './list/shipping-address.component';
import { ShippingAddressDetailComponent } from './detail/shipping-address-detail.component';
import { ShippingAddressUpdateComponent } from './update/shipping-address-update.component';
import { ShippingAddressDeleteDialogComponent } from './delete/shipping-address-delete-dialog.component';
import { ShippingAddressRoutingModule } from './route/shipping-address-routing.module';

@NgModule({
  imports: [SharedModule, ShippingAddressRoutingModule],
  declarations: [
    ShippingAddressComponent,
    ShippingAddressDetailComponent,
    ShippingAddressUpdateComponent,
    ShippingAddressDeleteDialogComponent,
  ],
  entryComponents: [ShippingAddressDeleteDialogComponent],
})
export class ShippingAddressModule {}
