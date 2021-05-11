import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { EcomMarkupSecondaryComponent } from './list/ecom-markup-secondary.component';
import { EcomMarkupSecondaryDetailComponent } from './detail/ecom-markup-secondary-detail.component';
import { EcomMarkupSecondaryUpdateComponent } from './update/ecom-markup-secondary-update.component';
import { EcomMarkupSecondaryDeleteDialogComponent } from './delete/ecom-markup-secondary-delete-dialog.component';
import { EcomMarkupSecondaryRoutingModule } from './route/ecom-markup-secondary-routing.module';

@NgModule({
  imports: [SharedModule, EcomMarkupSecondaryRoutingModule],
  declarations: [
    EcomMarkupSecondaryComponent,
    EcomMarkupSecondaryDetailComponent,
    EcomMarkupSecondaryUpdateComponent,
    EcomMarkupSecondaryDeleteDialogComponent,
  ],
  entryComponents: [EcomMarkupSecondaryDeleteDialogComponent],
})
export class EcomMarkupSecondaryModule {}
