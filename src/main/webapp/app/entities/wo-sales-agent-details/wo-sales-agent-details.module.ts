import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { WoSalesAgentDetailsComponent } from './wo-sales-agent-details.component';
import { WoSalesAgentDetailsDetailComponent } from './wo-sales-agent-details-detail.component';
import { WoSalesAgentDetailsUpdateComponent } from './wo-sales-agent-details-update.component';
import { WoSalesAgentDetailsDeleteDialogComponent } from './wo-sales-agent-details-delete-dialog.component';
import { woSalesAgentDetailsRoute } from './wo-sales-agent-details.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(woSalesAgentDetailsRoute)],
  declarations: [
    WoSalesAgentDetailsComponent,
    WoSalesAgentDetailsDetailComponent,
    WoSalesAgentDetailsUpdateComponent,
    WoSalesAgentDetailsDeleteDialogComponent
  ],
  entryComponents: [WoSalesAgentDetailsDeleteDialogComponent]
})
export class EshipperWoSalesAgentDetailsModule {}
