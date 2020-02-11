import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomOrderComponent } from './ecom-order.component';
import { EcomOrderDetailComponent } from './ecom-order-detail.component';
import { EcomOrderUpdateComponent } from './ecom-order-update.component';
import { EcomOrderDeleteDialogComponent } from './ecom-order-delete-dialog.component';
import { ecomOrderRoute } from './ecom-order.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomOrderRoute)],
  declarations: [EcomOrderComponent, EcomOrderDetailComponent, EcomOrderUpdateComponent, EcomOrderDeleteDialogComponent],
  entryComponents: [EcomOrderDeleteDialogComponent]
})
export class EshipperEcomOrderModule {}
