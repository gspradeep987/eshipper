import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomStoreImagesComponent } from './ecom-store-images.component';
import { EcomStoreImagesDetailComponent } from './ecom-store-images-detail.component';
import { EcomStoreImagesUpdateComponent } from './ecom-store-images-update.component';
import { EcomStoreImagesDeleteDialogComponent } from './ecom-store-images-delete-dialog.component';
import { ecomStoreImagesRoute } from './ecom-store-images.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomStoreImagesRoute)],
  declarations: [
    EcomStoreImagesComponent,
    EcomStoreImagesDetailComponent,
    EcomStoreImagesUpdateComponent,
    EcomStoreImagesDeleteDialogComponent,
  ],
  entryComponents: [EcomStoreImagesDeleteDialogComponent],
})
export class EshipperEcomStoreImagesModule {}
