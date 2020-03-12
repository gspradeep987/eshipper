import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomStoreSyncComponent } from './ecom-store-sync.component';
import { EcomStoreSyncDetailComponent } from './ecom-store-sync-detail.component';
import { EcomStoreSyncUpdateComponent } from './ecom-store-sync-update.component';
import { EcomStoreSyncDeleteDialogComponent } from './ecom-store-sync-delete-dialog.component';
import { ecomStoreSyncRoute } from './ecom-store-sync.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomStoreSyncRoute)],
  declarations: [EcomStoreSyncComponent, EcomStoreSyncDetailComponent, EcomStoreSyncUpdateComponent, EcomStoreSyncDeleteDialogComponent],
  entryComponents: [EcomStoreSyncDeleteDialogComponent]
})
export class EshipperEcomStoreSyncModule {}
