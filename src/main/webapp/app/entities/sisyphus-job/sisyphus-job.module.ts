import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { SisyphusJobComponent } from './list/sisyphus-job.component';
import { SisyphusJobDetailComponent } from './detail/sisyphus-job-detail.component';
import { SisyphusJobUpdateComponent } from './update/sisyphus-job-update.component';
import { SisyphusJobDeleteDialogComponent } from './delete/sisyphus-job-delete-dialog.component';
import { SisyphusJobRoutingModule } from './route/sisyphus-job-routing.module';

@NgModule({
  imports: [SharedModule, SisyphusJobRoutingModule],
  declarations: [SisyphusJobComponent, SisyphusJobDetailComponent, SisyphusJobUpdateComponent, SisyphusJobDeleteDialogComponent],
  entryComponents: [SisyphusJobDeleteDialogComponent],
})
export class SisyphusJobModule {}
