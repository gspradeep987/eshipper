import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { EcomProductImageComponent } from './list/ecom-product-image.component';
import { EcomProductImageDetailComponent } from './detail/ecom-product-image-detail.component';
import { EcomProductImageUpdateComponent } from './update/ecom-product-image-update.component';
import { EcomProductImageDeleteDialogComponent } from './delete/ecom-product-image-delete-dialog.component';
import { EcomProductImageRoutingModule } from './route/ecom-product-image-routing.module';

@NgModule({
  imports: [SharedModule, EcomProductImageRoutingModule],
  declarations: [
    EcomProductImageComponent,
    EcomProductImageDetailComponent,
    EcomProductImageUpdateComponent,
    EcomProductImageDeleteDialogComponent,
  ],
  entryComponents: [EcomProductImageDeleteDialogComponent],
})
export class EcomProductImageModule {}
