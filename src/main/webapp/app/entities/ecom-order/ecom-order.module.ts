import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { EcomOrderComponent } from './list/ecom-order.component';
import { EcomOrderDetailComponent } from './detail/ecom-order-detail.component';
import { EcomOrderUpdateComponent } from './update/ecom-order-update.component';
import { EcomOrderDeleteDialogComponent } from './delete/ecom-order-delete-dialog.component';
import { EcomOrderRoutingModule } from './route/ecom-order-routing.module';

@NgModule({
  imports: [SharedModule, EcomOrderRoutingModule],
  declarations: [EcomOrderComponent, EcomOrderDetailComponent, EcomOrderUpdateComponent, EcomOrderDeleteDialogComponent],
  entryComponents: [EcomOrderDeleteDialogComponent],
})
export class EcomOrderModule {}
