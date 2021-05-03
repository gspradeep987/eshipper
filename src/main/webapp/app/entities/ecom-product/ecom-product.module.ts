import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { EcomProductComponent } from './list/ecom-product.component';
import { EcomProductDetailComponent } from './detail/ecom-product-detail.component';
import { EcomProductUpdateComponent } from './update/ecom-product-update.component';
import { EcomProductDeleteDialogComponent } from './delete/ecom-product-delete-dialog.component';
import { EcomProductRoutingModule } from './route/ecom-product-routing.module';

@NgModule({
  imports: [SharedModule, EcomProductRoutingModule],
  declarations: [EcomProductComponent, EcomProductDetailComponent, EcomProductUpdateComponent, EcomProductDeleteDialogComponent],
  entryComponents: [EcomProductDeleteDialogComponent],
})
export class EcomProductModule {}
