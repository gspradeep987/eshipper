import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomOrderSerchTypeComponent } from './ecom-order-serch-type.component';
import { EcomOrderSerchTypeDetailComponent } from './ecom-order-serch-type-detail.component';
import { EcomOrderSerchTypeUpdateComponent } from './ecom-order-serch-type-update.component';
import { EcomOrderSerchTypeDeleteDialogComponent } from './ecom-order-serch-type-delete-dialog.component';
import { ecomOrderSerchTypeRoute } from './ecom-order-serch-type.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomOrderSerchTypeRoute)],
  declarations: [
    EcomOrderSerchTypeComponent,
    EcomOrderSerchTypeDetailComponent,
    EcomOrderSerchTypeUpdateComponent,
    EcomOrderSerchTypeDeleteDialogComponent,
  ],
  entryComponents: [EcomOrderSerchTypeDeleteDialogComponent],
})
export class EshipperEcomOrderSerchTypeModule {}
