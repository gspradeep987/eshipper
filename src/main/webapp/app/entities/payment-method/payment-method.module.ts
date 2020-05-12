import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { PaymentMethodComponent } from './payment-method.component';
import { PaymentMethodDetailComponent } from './payment-method-detail.component';
import { PaymentMethodUpdateComponent } from './payment-method-update.component';
import { PaymentMethodDeleteDialogComponent } from './payment-method-delete-dialog.component';
import { paymentMethodRoute } from './payment-method.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(paymentMethodRoute)],
  declarations: [PaymentMethodComponent, PaymentMethodDetailComponent, PaymentMethodUpdateComponent, PaymentMethodDeleteDialogComponent],
  entryComponents: [PaymentMethodDeleteDialogComponent]
})
export class EshipperPaymentMethodModule {}
