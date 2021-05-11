import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { SisyphusJobParamComponent } from './list/sisyphus-job-param.component';
import { SisyphusJobParamDetailComponent } from './detail/sisyphus-job-param-detail.component';
import { SisyphusJobParamUpdateComponent } from './update/sisyphus-job-param-update.component';
import { SisyphusJobParamDeleteDialogComponent } from './delete/sisyphus-job-param-delete-dialog.component';
import { SisyphusJobParamRoutingModule } from './route/sisyphus-job-param-routing.module';

@NgModule({
  imports: [SharedModule, SisyphusJobParamRoutingModule],
  declarations: [
    SisyphusJobParamComponent,
    SisyphusJobParamDetailComponent,
    SisyphusJobParamUpdateComponent,
    SisyphusJobParamDeleteDialogComponent,
  ],
  entryComponents: [SisyphusJobParamDeleteDialogComponent],
})
export class SisyphusJobParamModule {}
