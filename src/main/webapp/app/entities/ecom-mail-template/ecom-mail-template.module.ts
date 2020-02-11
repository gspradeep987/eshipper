import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomMailTemplateComponent } from './ecom-mail-template.component';
import { EcomMailTemplateDetailComponent } from './ecom-mail-template-detail.component';
import { EcomMailTemplateUpdateComponent } from './ecom-mail-template-update.component';
import { EcomMailTemplateDeleteDialogComponent } from './ecom-mail-template-delete-dialog.component';
import { ecomMailTemplateRoute } from './ecom-mail-template.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomMailTemplateRoute)],
  declarations: [
    EcomMailTemplateComponent,
    EcomMailTemplateDetailComponent,
    EcomMailTemplateUpdateComponent,
    EcomMailTemplateDeleteDialogComponent
  ],
  entryComponents: [EcomMailTemplateDeleteDialogComponent]
})
export class EshipperEcomMailTemplateModule {}
