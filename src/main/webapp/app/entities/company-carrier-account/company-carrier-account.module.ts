import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { CompanyCarrierAccountComponent } from './company-carrier-account.component';
import { CompanyCarrierAccountDetailComponent } from './company-carrier-account-detail.component';
import { CompanyCarrierAccountUpdateComponent } from './company-carrier-account-update.component';
import { CompanyCarrierAccountDeleteDialogComponent } from './company-carrier-account-delete-dialog.component';
import { companyCarrierAccountRoute } from './company-carrier-account.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(companyCarrierAccountRoute)],
  declarations: [
    CompanyCarrierAccountComponent,
    CompanyCarrierAccountDetailComponent,
    CompanyCarrierAccountUpdateComponent,
    CompanyCarrierAccountDeleteDialogComponent
  ],
  entryComponents: [CompanyCarrierAccountDeleteDialogComponent]
})
export class EshipperCompanyCarrierAccountModule {}
