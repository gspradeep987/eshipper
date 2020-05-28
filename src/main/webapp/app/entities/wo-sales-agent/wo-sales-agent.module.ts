import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { WoSalesAgentComponent } from './wo-sales-agent.component';
import { WoSalesAgentDetailComponent } from './wo-sales-agent-detail.component';
import { WoSalesAgentUpdateComponent } from './wo-sales-agent-update.component';
import { WoSalesAgentDeleteDialogComponent } from './wo-sales-agent-delete-dialog.component';
import { woSalesAgentRoute } from './wo-sales-agent.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(woSalesAgentRoute)],
  declarations: [WoSalesAgentComponent, WoSalesAgentDetailComponent, WoSalesAgentUpdateComponent, WoSalesAgentDeleteDialogComponent],
  entryComponents: [WoSalesAgentDeleteDialogComponent],
})
export class EshipperWoSalesAgentModule {}
