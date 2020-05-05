import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { SignatureRequiredComponent } from './signature-required.component';
import { SignatureRequiredDetailComponent } from './signature-required-detail.component';
import { SignatureRequiredUpdateComponent } from './signature-required-update.component';
import { SignatureRequiredDeleteDialogComponent } from './signature-required-delete-dialog.component';
import { signatureRequiredRoute } from './signature-required.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(signatureRequiredRoute)],
  declarations: [
    SignatureRequiredComponent,
    SignatureRequiredDetailComponent,
    SignatureRequiredUpdateComponent,
    SignatureRequiredDeleteDialogComponent
  ],
  entryComponents: [SignatureRequiredDeleteDialogComponent]
})
export class EshipperSignatureRequiredModule {}
