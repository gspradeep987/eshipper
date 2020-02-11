import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomMarkupQuaternaryComponent } from './ecom-markup-quaternary.component';
import { EcomMarkupQuaternaryDetailComponent } from './ecom-markup-quaternary-detail.component';
import { EcomMarkupQuaternaryUpdateComponent } from './ecom-markup-quaternary-update.component';
import { EcomMarkupQuaternaryDeleteDialogComponent } from './ecom-markup-quaternary-delete-dialog.component';
import { ecomMarkupQuaternaryRoute } from './ecom-markup-quaternary.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomMarkupQuaternaryRoute)],
  declarations: [
    EcomMarkupQuaternaryComponent,
    EcomMarkupQuaternaryDetailComponent,
    EcomMarkupQuaternaryUpdateComponent,
    EcomMarkupQuaternaryDeleteDialogComponent
  ],
  entryComponents: [EcomMarkupQuaternaryDeleteDialogComponent]
})
export class EshipperEcomMarkupQuaternaryModule {}
