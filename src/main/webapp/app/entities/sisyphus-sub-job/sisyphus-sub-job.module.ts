import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { SisyphusSubJobComponent } from './list/sisyphus-sub-job.component';
import { SisyphusSubJobDetailComponent } from './detail/sisyphus-sub-job-detail.component';
import { SisyphusSubJobUpdateComponent } from './update/sisyphus-sub-job-update.component';
import { SisyphusSubJobDeleteDialogComponent } from './delete/sisyphus-sub-job-delete-dialog.component';
import { SisyphusSubJobRoutingModule } from './route/sisyphus-sub-job-routing.module';

@NgModule({
  imports: [SharedModule, SisyphusSubJobRoutingModule],
  declarations: [
    SisyphusSubJobComponent,
    SisyphusSubJobDetailComponent,
    SisyphusSubJobUpdateComponent,
    SisyphusSubJobDeleteDialogComponent,
  ],
  entryComponents: [SisyphusSubJobDeleteDialogComponent],
})
export class SisyphusSubJobModule {}
