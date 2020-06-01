import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ElasticSearchWoSalesAgentComponent } from './elastic-search-wo-sales-agent.component';
import { ElasticSearchWoSalesAgentDetailComponent } from './elastic-search-wo-sales-agent-detail.component';
import { ElasticSearchWoSalesAgentUpdateComponent } from './elastic-search-wo-sales-agent-update.component';
import { ElasticSearchWoSalesAgentDeleteDialogComponent } from './elastic-search-wo-sales-agent-delete-dialog.component';
import { elasticSearchWoSalesAgentRoute } from './elastic-search-wo-sales-agent.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(elasticSearchWoSalesAgentRoute)],
  declarations: [
    ElasticSearchWoSalesAgentComponent,
    ElasticSearchWoSalesAgentDetailComponent,
    ElasticSearchWoSalesAgentUpdateComponent,
    ElasticSearchWoSalesAgentDeleteDialogComponent,
  ],
  entryComponents: [ElasticSearchWoSalesAgentDeleteDialogComponent],
})
export class EshipperElasticSearchWoSalesAgentModule {}
