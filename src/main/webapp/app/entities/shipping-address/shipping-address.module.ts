import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ShippingAddressComponent } from './shipping-address.component';
import { ShippingAddressDetailComponent } from './shipping-address-detail.component';
import { ShippingAddressUpdateComponent } from './shipping-address-update.component';
import { ShippingAddressDeleteDialogComponent } from './shipping-address-delete-dialog.component';
import { shippingAddressRoute } from './shipping-address.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(shippingAddressRoute)],
  declarations: [
    ShippingAddressComponent,
    ShippingAddressDetailComponent,
    ShippingAddressUpdateComponent,
    ShippingAddressDeleteDialogComponent
  ],
  entryComponents: [ShippingAddressDeleteDialogComponent]
})
export class EshipperShippingAddressModule {}
