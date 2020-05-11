import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { CarrierServiceComponent } from './carrier-service.component';
import { CarrierServiceDetailComponent } from './carrier-service-detail.component';
import { CarrierServiceUpdateComponent } from './carrier-service-update.component';
import { CarrierServiceDeleteDialogComponent } from './carrier-service-delete-dialog.component';
import { carrierServiceRoute } from './carrier-service.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(carrierServiceRoute)],
  declarations: [
    CarrierServiceComponent,
    CarrierServiceDetailComponent,
    CarrierServiceUpdateComponent,
    CarrierServiceDeleteDialogComponent
  ],
  entryComponents: [CarrierServiceDeleteDialogComponent]
})
export class EshipperCarrierServiceModule {}
