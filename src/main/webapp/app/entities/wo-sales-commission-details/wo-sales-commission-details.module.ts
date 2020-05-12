import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { WoSalesCommissionDetailsComponent } from './wo-sales-commission-details.component';
import { WoSalesCommissionDetailsDetailComponent } from './wo-sales-commission-details-detail.component';
import { WoSalesCommissionDetailsUpdateComponent } from './wo-sales-commission-details-update.component';
import { WoSalesCommissionDetailsDeleteDialogComponent } from './wo-sales-commission-details-delete-dialog.component';
import { woSalesCommissionDetailsRoute } from './wo-sales-commission-details.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(woSalesCommissionDetailsRoute)],
  declarations: [
    WoSalesCommissionDetailsComponent,
    WoSalesCommissionDetailsDetailComponent,
    WoSalesCommissionDetailsUpdateComponent,
    WoSalesCommissionDetailsDeleteDialogComponent
  ],
  entryComponents: [WoSalesCommissionDetailsDeleteDialogComponent]
})
export class EshipperWoSalesCommissionDetailsModule {}
