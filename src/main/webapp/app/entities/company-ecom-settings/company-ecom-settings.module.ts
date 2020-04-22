import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { CompanyEcomSettingsComponent } from './company-ecom-settings.component';
import { CompanyEcomSettingsDetailComponent } from './company-ecom-settings-detail.component';
import { CompanyEcomSettingsUpdateComponent } from './company-ecom-settings-update.component';
import { CompanyEcomSettingsDeleteDialogComponent } from './company-ecom-settings-delete-dialog.component';
import { companyEcomSettingsRoute } from './company-ecom-settings.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(companyEcomSettingsRoute)],
  declarations: [
    CompanyEcomSettingsComponent,
    CompanyEcomSettingsDetailComponent,
    CompanyEcomSettingsUpdateComponent,
    CompanyEcomSettingsDeleteDialogComponent
  ],
  entryComponents: [CompanyEcomSettingsDeleteDialogComponent]
})
export class EshipperCompanyEcomSettingsModule {}
