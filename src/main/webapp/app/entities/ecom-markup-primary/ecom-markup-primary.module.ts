import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { EcomMarkupPrimaryComponent } from './list/ecom-markup-primary.component';
import { EcomMarkupPrimaryDetailComponent } from './detail/ecom-markup-primary-detail.component';
import { EcomMarkupPrimaryUpdateComponent } from './update/ecom-markup-primary-update.component';
import { EcomMarkupPrimaryDeleteDialogComponent } from './delete/ecom-markup-primary-delete-dialog.component';
import { EcomMarkupPrimaryRoutingModule } from './route/ecom-markup-primary-routing.module';

@NgModule({
  imports: [SharedModule, EcomMarkupPrimaryRoutingModule],
  declarations: [
    EcomMarkupPrimaryComponent,
    EcomMarkupPrimaryDetailComponent,
    EcomMarkupPrimaryUpdateComponent,
    EcomMarkupPrimaryDeleteDialogComponent,
  ],
  entryComponents: [EcomMarkupPrimaryDeleteDialogComponent],
})
export class EcomMarkupPrimaryModule {}
