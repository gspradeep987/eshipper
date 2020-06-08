import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { AffiliateComponent } from './affiliate.component';
import { AffiliateDetailComponent } from './affiliate-detail.component';
import { AffiliateUpdateComponent } from './affiliate-update.component';
import { AffiliateDeleteDialogComponent } from './affiliate-delete-dialog.component';
import { affiliateRoute } from './affiliate.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(affiliateRoute)],
  declarations: [AffiliateComponent, AffiliateDetailComponent, AffiliateUpdateComponent, AffiliateDeleteDialogComponent],
  entryComponents: [AffiliateDeleteDialogComponent],
})
export class EshipperAffiliateModule {}
