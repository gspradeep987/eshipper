import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomorderAttachmentComponent } from './ecomorder-attachment.component';
import { EcomorderAttachmentDetailComponent } from './ecomorder-attachment-detail.component';
import { EcomorderAttachmentUpdateComponent } from './ecomorder-attachment-update.component';
import { EcomorderAttachmentDeleteDialogComponent } from './ecomorder-attachment-delete-dialog.component';
import { ecomorderAttachmentRoute } from './ecomorder-attachment.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomorderAttachmentRoute)],
  declarations: [
    EcomorderAttachmentComponent,
    EcomorderAttachmentDetailComponent,
    EcomorderAttachmentUpdateComponent,
    EcomorderAttachmentDeleteDialogComponent
  ],
  entryComponents: [EcomorderAttachmentDeleteDialogComponent]
})
export class EshipperEcomorderAttachmentModule {}
