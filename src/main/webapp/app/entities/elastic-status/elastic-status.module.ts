import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ElasticStatusComponent } from './elastic-status.component';
import { ElasticStatusDetailComponent } from './elastic-status-detail.component';
import { ElasticStatusUpdateComponent } from './elastic-status-update.component';
import { ElasticStatusDeleteDialogComponent } from './elastic-status-delete-dialog.component';
import { elasticStatusRoute } from './elastic-status.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(elasticStatusRoute)],
  declarations: [ElasticStatusComponent, ElasticStatusDetailComponent, ElasticStatusUpdateComponent, ElasticStatusDeleteDialogComponent],
  entryComponents: [ElasticStatusDeleteDialogComponent],
})
export class EshipperElasticStatusModule {}
