import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { EcomMarkupQuaternaryComponent } from './list/ecom-markup-quaternary.component';
import { EcomMarkupQuaternaryDetailComponent } from './detail/ecom-markup-quaternary-detail.component';
import { EcomMarkupQuaternaryUpdateComponent } from './update/ecom-markup-quaternary-update.component';
import { EcomMarkupQuaternaryDeleteDialogComponent } from './delete/ecom-markup-quaternary-delete-dialog.component';
import { EcomMarkupQuaternaryRoutingModule } from './route/ecom-markup-quaternary-routing.module';

@NgModule({
  imports: [SharedModule, EcomMarkupQuaternaryRoutingModule],
  declarations: [
    EcomMarkupQuaternaryComponent,
    EcomMarkupQuaternaryDetailComponent,
    EcomMarkupQuaternaryUpdateComponent,
    EcomMarkupQuaternaryDeleteDialogComponent,
  ],
  entryComponents: [EcomMarkupQuaternaryDeleteDialogComponent],
})
export class EcomMarkupQuaternaryModule {}
