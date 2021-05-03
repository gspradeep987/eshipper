import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { JobTypeComponent } from './list/job-type.component';
import { JobTypeDetailComponent } from './detail/job-type-detail.component';
import { JobTypeUpdateComponent } from './update/job-type-update.component';
import { JobTypeDeleteDialogComponent } from './delete/job-type-delete-dialog.component';
import { JobTypeRoutingModule } from './route/job-type-routing.module';

@NgModule({
  imports: [SharedModule, JobTypeRoutingModule],
  declarations: [JobTypeComponent, JobTypeDetailComponent, JobTypeUpdateComponent, JobTypeDeleteDialogComponent],
  entryComponents: [JobTypeDeleteDialogComponent],
})
export class JobTypeModule {}
