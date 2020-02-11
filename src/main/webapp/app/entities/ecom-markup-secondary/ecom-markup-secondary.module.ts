import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomMarkupSecondaryComponent } from './ecom-markup-secondary.component';
import { EcomMarkupSecondaryDetailComponent } from './ecom-markup-secondary-detail.component';
import { EcomMarkupSecondaryUpdateComponent } from './ecom-markup-secondary-update.component';
import { EcomMarkupSecondaryDeleteDialogComponent } from './ecom-markup-secondary-delete-dialog.component';
import { ecomMarkupSecondaryRoute } from './ecom-markup-secondary.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomMarkupSecondaryRoute)],
  declarations: [
    EcomMarkupSecondaryComponent,
    EcomMarkupSecondaryDetailComponent,
    EcomMarkupSecondaryUpdateComponent,
    EcomMarkupSecondaryDeleteDialogComponent
  ],
  entryComponents: [EcomMarkupSecondaryDeleteDialogComponent]
})
export class EshipperEcomMarkupSecondaryModule {}
