import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomOrderPaymentStatusComponent } from './ecom-order-payment-status.component';
import { EcomOrderPaymentStatusDetailComponent } from './ecom-order-payment-status-detail.component';
import { EcomOrderPaymentStatusUpdateComponent } from './ecom-order-payment-status-update.component';
import { EcomOrderPaymentStatusDeleteDialogComponent } from './ecom-order-payment-status-delete-dialog.component';
import { ecomOrderPaymentStatusRoute } from './ecom-order-payment-status.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomOrderPaymentStatusRoute)],
  declarations: [
    EcomOrderPaymentStatusComponent,
    EcomOrderPaymentStatusDetailComponent,
    EcomOrderPaymentStatusUpdateComponent,
    EcomOrderPaymentStatusDeleteDialogComponent,
  ],
  entryComponents: [EcomOrderPaymentStatusDeleteDialogComponent],
})
export class EshipperEcomOrderPaymentStatusModule {}
