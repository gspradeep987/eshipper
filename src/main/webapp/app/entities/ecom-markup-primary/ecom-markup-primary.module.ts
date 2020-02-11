import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomMarkupPrimaryComponent } from './ecom-markup-primary.component';
import { EcomMarkupPrimaryDetailComponent } from './ecom-markup-primary-detail.component';
import { EcomMarkupPrimaryUpdateComponent } from './ecom-markup-primary-update.component';
import { EcomMarkupPrimaryDeleteDialogComponent } from './ecom-markup-primary-delete-dialog.component';
import { ecomMarkupPrimaryRoute } from './ecom-markup-primary.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomMarkupPrimaryRoute)],
  declarations: [
    EcomMarkupPrimaryComponent,
    EcomMarkupPrimaryDetailComponent,
    EcomMarkupPrimaryUpdateComponent,
    EcomMarkupPrimaryDeleteDialogComponent
  ],
  entryComponents: [EcomMarkupPrimaryDeleteDialogComponent]
})
export class EshipperEcomMarkupPrimaryModule {}
