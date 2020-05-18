import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomAutomationRulesComponent } from './ecom-automation-rules.component';
import { EcomAutomationRulesDetailComponent } from './ecom-automation-rules-detail.component';
import { EcomAutomationRulesUpdateComponent } from './ecom-automation-rules-update.component';
import { EcomAutomationRulesDeleteDialogComponent } from './ecom-automation-rules-delete-dialog.component';
import { ecomAutomationRulesRoute } from './ecom-automation-rules.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomAutomationRulesRoute)],
  declarations: [
    EcomAutomationRulesComponent,
    EcomAutomationRulesDetailComponent,
    EcomAutomationRulesUpdateComponent,
    EcomAutomationRulesDeleteDialogComponent,
  ],
  entryComponents: [EcomAutomationRulesDeleteDialogComponent],
})
export class EshipperEcomAutomationRulesModule {}
