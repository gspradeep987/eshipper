import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { EcomStoreSyncComponent } from './list/ecom-store-sync.component';
import { EcomStoreSyncDetailComponent } from './detail/ecom-store-sync-detail.component';
import { EcomStoreSyncUpdateComponent } from './update/ecom-store-sync-update.component';
import { EcomStoreSyncDeleteDialogComponent } from './delete/ecom-store-sync-delete-dialog.component';
import { EcomStoreSyncRoutingModule } from './route/ecom-store-sync-routing.module';

@NgModule({
  imports: [SharedModule, EcomStoreSyncRoutingModule],
  declarations: [EcomStoreSyncComponent, EcomStoreSyncDetailComponent, EcomStoreSyncUpdateComponent, EcomStoreSyncDeleteDialogComponent],
  entryComponents: [EcomStoreSyncDeleteDialogComponent],
})
export class EcomStoreSyncModule {}
