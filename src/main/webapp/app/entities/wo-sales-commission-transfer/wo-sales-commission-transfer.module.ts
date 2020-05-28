import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { WoSalesCommissionTransferComponent } from './wo-sales-commission-transfer.component';
import { WoSalesCommissionTransferDetailComponent } from './wo-sales-commission-transfer-detail.component';
import { WoSalesCommissionTransferUpdateComponent } from './wo-sales-commission-transfer-update.component';
import { WoSalesCommissionTransferDeleteDialogComponent } from './wo-sales-commission-transfer-delete-dialog.component';
import { woSalesCommissionTransferRoute } from './wo-sales-commission-transfer.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(woSalesCommissionTransferRoute)],
  declarations: [
    WoSalesCommissionTransferComponent,
    WoSalesCommissionTransferDetailComponent,
    WoSalesCommissionTransferUpdateComponent,
    WoSalesCommissionTransferDeleteDialogComponent,
  ],
  entryComponents: [WoSalesCommissionTransferDeleteDialogComponent],
})
export class EshipperWoSalesCommissionTransferModule {}
