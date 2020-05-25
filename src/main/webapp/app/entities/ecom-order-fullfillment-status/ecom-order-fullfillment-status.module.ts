import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomOrderFullfillmentStatusComponent } from './ecom-order-fullfillment-status.component';
import { EcomOrderFullfillmentStatusDetailComponent } from './ecom-order-fullfillment-status-detail.component';
import { EcomOrderFullfillmentStatusUpdateComponent } from './ecom-order-fullfillment-status-update.component';
import { EcomOrderFullfillmentStatusDeleteDialogComponent } from './ecom-order-fullfillment-status-delete-dialog.component';
import { ecomOrderFullfillmentStatusRoute } from './ecom-order-fullfillment-status.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomOrderFullfillmentStatusRoute)],
  declarations: [
    EcomOrderFullfillmentStatusComponent,
    EcomOrderFullfillmentStatusDetailComponent,
    EcomOrderFullfillmentStatusUpdateComponent,
    EcomOrderFullfillmentStatusDeleteDialogComponent,
  ],
  entryComponents: [EcomOrderFullfillmentStatusDeleteDialogComponent],
})
export class EshipperEcomOrderFullfillmentStatusModule {}
