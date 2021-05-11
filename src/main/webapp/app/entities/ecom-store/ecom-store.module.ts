import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { EcomStoreComponent } from './list/ecom-store.component';
import { EcomStoreDetailComponent } from './detail/ecom-store-detail.component';
import { EcomStoreUpdateComponent } from './update/ecom-store-update.component';
import { EcomStoreDeleteDialogComponent } from './delete/ecom-store-delete-dialog.component';
import { EcomStoreRoutingModule } from './route/ecom-store-routing.module';

@NgModule({
  imports: [SharedModule, EcomStoreRoutingModule],
  declarations: [EcomStoreComponent, EcomStoreDetailComponent, EcomStoreUpdateComponent, EcomStoreDeleteDialogComponent],
  entryComponents: [EcomStoreDeleteDialogComponent],
})
export class EcomStoreModule {}
