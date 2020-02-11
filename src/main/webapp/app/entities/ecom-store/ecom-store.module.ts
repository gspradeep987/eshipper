import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomStoreComponent } from './ecom-store.component';
import { EcomStoreDetailComponent } from './ecom-store-detail.component';
import { EcomStoreUpdateComponent } from './ecom-store-update.component';
import { EcomStoreDeleteDialogComponent } from './ecom-store-delete-dialog.component';
import { ecomStoreRoute } from './ecom-store.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomStoreRoute)],
  declarations: [EcomStoreComponent, EcomStoreDetailComponent, EcomStoreUpdateComponent, EcomStoreDeleteDialogComponent],
  entryComponents: [EcomStoreDeleteDialogComponent]
})
export class EshipperEcomStoreModule {}
