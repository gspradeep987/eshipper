import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomMarkupTertiaryComponent } from './ecom-markup-tertiary.component';
import { EcomMarkupTertiaryDetailComponent } from './ecom-markup-tertiary-detail.component';
import { EcomMarkupTertiaryUpdateComponent } from './ecom-markup-tertiary-update.component';
import { EcomMarkupTertiaryDeleteDialogComponent } from './ecom-markup-tertiary-delete-dialog.component';
import { ecomMarkupTertiaryRoute } from './ecom-markup-tertiary.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomMarkupTertiaryRoute)],
  declarations: [
    EcomMarkupTertiaryComponent,
    EcomMarkupTertiaryDetailComponent,
    EcomMarkupTertiaryUpdateComponent,
    EcomMarkupTertiaryDeleteDialogComponent
  ],
  entryComponents: [EcomMarkupTertiaryDeleteDialogComponent]
})
export class EshipperEcomMarkupTertiaryModule {}
