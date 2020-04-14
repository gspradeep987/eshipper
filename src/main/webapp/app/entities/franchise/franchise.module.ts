import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { FranchiseComponent } from './franchise.component';
import { FranchiseDetailComponent } from './franchise-detail.component';
import { FranchiseUpdateComponent } from './franchise-update.component';
import { FranchiseDeleteDialogComponent } from './franchise-delete-dialog.component';
import { franchiseRoute } from './franchise.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(franchiseRoute)],
  declarations: [FranchiseComponent, FranchiseDetailComponent, FranchiseUpdateComponent, FranchiseDeleteDialogComponent],
  entryComponents: [FranchiseDeleteDialogComponent]
})
export class EshipperFranchiseModule {}
