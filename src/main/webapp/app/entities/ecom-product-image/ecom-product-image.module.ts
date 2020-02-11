import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomProductImageComponent } from './ecom-product-image.component';
import { EcomProductImageDetailComponent } from './ecom-product-image-detail.component';
import { EcomProductImageUpdateComponent } from './ecom-product-image-update.component';
import { EcomProductImageDeleteDialogComponent } from './ecom-product-image-delete-dialog.component';
import { ecomProductImageRoute } from './ecom-product-image.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomProductImageRoute)],
  declarations: [
    EcomProductImageComponent,
    EcomProductImageDetailComponent,
    EcomProductImageUpdateComponent,
    EcomProductImageDeleteDialogComponent
  ],
  entryComponents: [EcomProductImageDeleteDialogComponent]
})
export class EshipperEcomProductImageModule {}
