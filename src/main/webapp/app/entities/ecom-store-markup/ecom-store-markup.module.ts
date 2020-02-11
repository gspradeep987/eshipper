import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomStoreMarkupComponent } from './ecom-store-markup.component';
import { EcomStoreMarkupDetailComponent } from './ecom-store-markup-detail.component';
import { EcomStoreMarkupUpdateComponent } from './ecom-store-markup-update.component';
import { EcomStoreMarkupDeleteDialogComponent } from './ecom-store-markup-delete-dialog.component';
import { ecomStoreMarkupRoute } from './ecom-store-markup.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomStoreMarkupRoute)],
  declarations: [
    EcomStoreMarkupComponent,
    EcomStoreMarkupDetailComponent,
    EcomStoreMarkupUpdateComponent,
    EcomStoreMarkupDeleteDialogComponent
  ],
  entryComponents: [EcomStoreMarkupDeleteDialogComponent]
})
export class EshipperEcomStoreMarkupModule {}
