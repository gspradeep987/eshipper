import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { WoSalesOperationalDetailsComponent } from './wo-sales-operational-details.component';
import { WoSalesOperationalDetailsDetailComponent } from './wo-sales-operational-details-detail.component';
import { WoSalesOperationalDetailsUpdateComponent } from './wo-sales-operational-details-update.component';
import { WoSalesOperationalDetailsDeleteDialogComponent } from './wo-sales-operational-details-delete-dialog.component';
import { woSalesOperationalDetailsRoute } from './wo-sales-operational-details.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(woSalesOperationalDetailsRoute)],
  declarations: [
    WoSalesOperationalDetailsComponent,
    WoSalesOperationalDetailsDetailComponent,
    WoSalesOperationalDetailsUpdateComponent,
    WoSalesOperationalDetailsDeleteDialogComponent,
  ],
  entryComponents: [WoSalesOperationalDetailsDeleteDialogComponent],
})
export class EshipperWoSalesOperationalDetailsModule {}
