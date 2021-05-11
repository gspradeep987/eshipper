import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { SisyphusJobTypeComponent } from './list/sisyphus-job-type.component';
import { SisyphusJobTypeDetailComponent } from './detail/sisyphus-job-type-detail.component';
import { SisyphusJobTypeUpdateComponent } from './update/sisyphus-job-type-update.component';
import { SisyphusJobTypeDeleteDialogComponent } from './delete/sisyphus-job-type-delete-dialog.component';
import { SisyphusJobTypeRoutingModule } from './route/sisyphus-job-type-routing.module';

@NgModule({
  imports: [SharedModule, SisyphusJobTypeRoutingModule],
  declarations: [
    SisyphusJobTypeComponent,
    SisyphusJobTypeDetailComponent,
    SisyphusJobTypeUpdateComponent,
    SisyphusJobTypeDeleteDialogComponent,
  ],
  entryComponents: [SisyphusJobTypeDeleteDialogComponent],
})
export class SisyphusJobTypeModule {}
