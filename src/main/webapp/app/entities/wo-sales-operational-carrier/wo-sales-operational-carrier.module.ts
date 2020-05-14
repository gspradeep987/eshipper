import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { WoSalesOperationalCarrierComponent } from './wo-sales-operational-carrier.component';
import { WoSalesOperationalCarrierDetailComponent } from './wo-sales-operational-carrier-detail.component';
import { WoSalesOperationalCarrierUpdateComponent } from './wo-sales-operational-carrier-update.component';
import { WoSalesOperationalCarrierDeleteDialogComponent } from './wo-sales-operational-carrier-delete-dialog.component';
import { woSalesOperationalCarrierRoute } from './wo-sales-operational-carrier.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(woSalesOperationalCarrierRoute)],
  declarations: [
    WoSalesOperationalCarrierComponent,
    WoSalesOperationalCarrierDetailComponent,
    WoSalesOperationalCarrierUpdateComponent,
    WoSalesOperationalCarrierDeleteDialogComponent
  ],
  entryComponents: [WoSalesOperationalCarrierDeleteDialogComponent]
})
export class EshipperWoSalesOperationalCarrierModule {}
