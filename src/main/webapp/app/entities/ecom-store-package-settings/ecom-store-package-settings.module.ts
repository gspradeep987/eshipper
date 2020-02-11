import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomStorePackageSettingsComponent } from './ecom-store-package-settings.component';
import { EcomStorePackageSettingsDetailComponent } from './ecom-store-package-settings-detail.component';
import { EcomStorePackageSettingsUpdateComponent } from './ecom-store-package-settings-update.component';
import { EcomStorePackageSettingsDeleteDialogComponent } from './ecom-store-package-settings-delete-dialog.component';
import { ecomStorePackageSettingsRoute } from './ecom-store-package-settings.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomStorePackageSettingsRoute)],
  declarations: [
    EcomStorePackageSettingsComponent,
    EcomStorePackageSettingsDetailComponent,
    EcomStorePackageSettingsUpdateComponent,
    EcomStorePackageSettingsDeleteDialogComponent
  ],
  entryComponents: [EcomStorePackageSettingsDeleteDialogComponent]
})
export class EshipperEcomStorePackageSettingsModule {}
