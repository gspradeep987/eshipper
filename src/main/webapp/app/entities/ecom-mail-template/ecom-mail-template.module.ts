import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { EcomMailTemplateComponent } from './list/ecom-mail-template.component';
import { EcomMailTemplateDetailComponent } from './detail/ecom-mail-template-detail.component';
import { EcomMailTemplateUpdateComponent } from './update/ecom-mail-template-update.component';
import { EcomMailTemplateDeleteDialogComponent } from './delete/ecom-mail-template-delete-dialog.component';
import { EcomMailTemplateRoutingModule } from './route/ecom-mail-template-routing.module';

@NgModule({
  imports: [SharedModule, EcomMailTemplateRoutingModule],
  declarations: [
    EcomMailTemplateComponent,
    EcomMailTemplateDetailComponent,
    EcomMailTemplateUpdateComponent,
    EcomMailTemplateDeleteDialogComponent,
  ],
  entryComponents: [EcomMailTemplateDeleteDialogComponent],
})
export class EcomMailTemplateModule {}
