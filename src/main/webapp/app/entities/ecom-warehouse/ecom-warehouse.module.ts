import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { EcomWarehouseComponent } from './list/ecom-warehouse.component';
import { EcomWarehouseDetailComponent } from './detail/ecom-warehouse-detail.component';
import { EcomWarehouseUpdateComponent } from './update/ecom-warehouse-update.component';
import { EcomWarehouseDeleteDialogComponent } from './delete/ecom-warehouse-delete-dialog.component';
import { EcomWarehouseRoutingModule } from './route/ecom-warehouse-routing.module';

@NgModule({
  imports: [SharedModule, EcomWarehouseRoutingModule],
  declarations: [EcomWarehouseComponent, EcomWarehouseDetailComponent, EcomWarehouseUpdateComponent, EcomWarehouseDeleteDialogComponent],
  entryComponents: [EcomWarehouseDeleteDialogComponent],
})
export class EcomWarehouseModule {}
