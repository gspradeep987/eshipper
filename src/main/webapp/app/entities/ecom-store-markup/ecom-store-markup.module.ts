import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { EcomStoreMarkupComponent } from './list/ecom-store-markup.component';
import { EcomStoreMarkupDetailComponent } from './detail/ecom-store-markup-detail.component';
import { EcomStoreMarkupUpdateComponent } from './update/ecom-store-markup-update.component';
import { EcomStoreMarkupDeleteDialogComponent } from './delete/ecom-store-markup-delete-dialog.component';
import { EcomStoreMarkupRoutingModule } from './route/ecom-store-markup-routing.module';

@NgModule({
  imports: [SharedModule, EcomStoreMarkupRoutingModule],
  declarations: [
    EcomStoreMarkupComponent,
    EcomStoreMarkupDetailComponent,
    EcomStoreMarkupUpdateComponent,
    EcomStoreMarkupDeleteDialogComponent,
  ],
  entryComponents: [EcomStoreMarkupDeleteDialogComponent],
})
export class EcomStoreMarkupModule {}
