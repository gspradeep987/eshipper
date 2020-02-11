import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomProductComponent } from './ecom-product.component';
import { EcomProductDetailComponent } from './ecom-product-detail.component';
import { EcomProductUpdateComponent } from './ecom-product-update.component';
import { EcomProductDeleteDialogComponent } from './ecom-product-delete-dialog.component';
import { ecomProductRoute } from './ecom-product.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomProductRoute)],
  declarations: [EcomProductComponent, EcomProductDetailComponent, EcomProductUpdateComponent, EcomProductDeleteDialogComponent],
  entryComponents: [EcomProductDeleteDialogComponent]
})
export class EshipperEcomProductModule {}
