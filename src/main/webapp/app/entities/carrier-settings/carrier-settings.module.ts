import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { CarrierSettingsComponent } from './carrier-settings.component';
import { CarrierSettingsDetailComponent } from './carrier-settings-detail.component';
import { CarrierSettingsUpdateComponent } from './carrier-settings-update.component';
import { CarrierSettingsDeleteDialogComponent } from './carrier-settings-delete-dialog.component';
import { carrierSettingsRoute } from './carrier-settings.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(carrierSettingsRoute)],
  declarations: [
    CarrierSettingsComponent,
    CarrierSettingsDetailComponent,
    CarrierSettingsUpdateComponent,
    CarrierSettingsDeleteDialogComponent
  ],
  entryComponents: [CarrierSettingsDeleteDialogComponent]
})
export class EshipperCarrierSettingsModule {}
