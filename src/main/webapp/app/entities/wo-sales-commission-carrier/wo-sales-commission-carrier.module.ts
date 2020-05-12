import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { WoSalesCommissionCarrierComponent } from './wo-sales-commission-carrier.component';
import { WoSalesCommissionCarrierDetailComponent } from './wo-sales-commission-carrier-detail.component';
import { WoSalesCommissionCarrierUpdateComponent } from './wo-sales-commission-carrier-update.component';
import { WoSalesCommissionCarrierDeleteDialogComponent } from './wo-sales-commission-carrier-delete-dialog.component';
import { woSalesCommissionCarrierRoute } from './wo-sales-commission-carrier.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(woSalesCommissionCarrierRoute)],
  declarations: [
    WoSalesCommissionCarrierComponent,
    WoSalesCommissionCarrierDetailComponent,
    WoSalesCommissionCarrierUpdateComponent,
    WoSalesCommissionCarrierDeleteDialogComponent
  ],
  entryComponents: [WoSalesCommissionCarrierDeleteDialogComponent]
})
export class EshipperWoSalesCommissionCarrierModule {}
