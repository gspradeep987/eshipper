import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomOrderAttachmentComponent } from './ecom-order-attachment.component';
import { EcomOrderAttachmentDetailComponent } from './ecom-order-attachment-detail.component';
import { EcomOrderAttachmentUpdateComponent } from './ecom-order-attachment-update.component';
import { EcomOrderAttachmentDeleteDialogComponent } from './ecom-order-attachment-delete-dialog.component';
import { ecomOrderAttachmentRoute } from './ecom-order-attachment.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomOrderAttachmentRoute)],
  declarations: [
    EcomOrderAttachmentComponent,
    EcomOrderAttachmentDetailComponent,
    EcomOrderAttachmentUpdateComponent,
    EcomOrderAttachmentDeleteDialogComponent
  ],
  entryComponents: [EcomOrderAttachmentDeleteDialogComponent]
})
export class EshipperEcomOrderAttachmentModule {}
