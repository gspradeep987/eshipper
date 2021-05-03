import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { EcomMarkupTertiaryComponent } from './list/ecom-markup-tertiary.component';
import { EcomMarkupTertiaryDetailComponent } from './detail/ecom-markup-tertiary-detail.component';
import { EcomMarkupTertiaryUpdateComponent } from './update/ecom-markup-tertiary-update.component';
import { EcomMarkupTertiaryDeleteDialogComponent } from './delete/ecom-markup-tertiary-delete-dialog.component';
import { EcomMarkupTertiaryRoutingModule } from './route/ecom-markup-tertiary-routing.module';

@NgModule({
  imports: [SharedModule, EcomMarkupTertiaryRoutingModule],
  declarations: [
    EcomMarkupTertiaryComponent,
    EcomMarkupTertiaryDetailComponent,
    EcomMarkupTertiaryUpdateComponent,
    EcomMarkupTertiaryDeleteDialogComponent,
  ],
  entryComponents: [EcomMarkupTertiaryDeleteDialogComponent],
})
export class EcomMarkupTertiaryModule {}
