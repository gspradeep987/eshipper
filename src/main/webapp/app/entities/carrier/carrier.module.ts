import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { CarrierComponent } from './carrier.component';
import { CarrierDetailComponent } from './carrier-detail.component';
import { CarrierUpdateComponent } from './carrier-update.component';
import { CarrierDeleteDialogComponent } from './carrier-delete-dialog.component';
import { carrierRoute } from './carrier.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(carrierRoute)],
  declarations: [CarrierComponent, CarrierDetailComponent, CarrierUpdateComponent, CarrierDeleteDialogComponent],
  entryComponents: [CarrierDeleteDialogComponent],
})
export class EshipperCarrierModule {}
