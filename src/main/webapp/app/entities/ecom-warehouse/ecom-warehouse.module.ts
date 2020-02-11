import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomWarehouseComponent } from './ecom-warehouse.component';
import { EcomWarehouseDetailComponent } from './ecom-warehouse-detail.component';
import { EcomWarehouseUpdateComponent } from './ecom-warehouse-update.component';
import { EcomWarehouseDeleteDialogComponent } from './ecom-warehouse-delete-dialog.component';
import { ecomWarehouseRoute } from './ecom-warehouse.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomWarehouseRoute)],
  declarations: [EcomWarehouseComponent, EcomWarehouseDetailComponent, EcomWarehouseUpdateComponent, EcomWarehouseDeleteDialogComponent],
  entryComponents: [EcomWarehouseDeleteDialogComponent]
})
export class EshipperEcomWarehouseModule {}
