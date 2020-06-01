import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { SalesAgentTypeComponent } from './sales-agent-type.component';
import { SalesAgentTypeDetailComponent } from './sales-agent-type-detail.component';
import { SalesAgentTypeUpdateComponent } from './sales-agent-type-update.component';
import { SalesAgentTypeDeleteDialogComponent } from './sales-agent-type-delete-dialog.component';
import { salesAgentTypeRoute } from './sales-agent-type.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(salesAgentTypeRoute)],
  declarations: [
    SalesAgentTypeComponent,
    SalesAgentTypeDetailComponent,
    SalesAgentTypeUpdateComponent,
    SalesAgentTypeDeleteDialogComponent,
  ],
  entryComponents: [SalesAgentTypeDeleteDialogComponent],
})
export class EshipperSalesAgentTypeModule {}
