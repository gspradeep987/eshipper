import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { EcomStorePackageSettingsComponent } from './list/ecom-store-package-settings.component';
import { EcomStorePackageSettingsDetailComponent } from './detail/ecom-store-package-settings-detail.component';
import { EcomStorePackageSettingsUpdateComponent } from './update/ecom-store-package-settings-update.component';
import { EcomStorePackageSettingsDeleteDialogComponent } from './delete/ecom-store-package-settings-delete-dialog.component';
import { EcomStorePackageSettingsRoutingModule } from './route/ecom-store-package-settings-routing.module';

@NgModule({
  imports: [SharedModule, EcomStorePackageSettingsRoutingModule],
  declarations: [
    EcomStorePackageSettingsComponent,
    EcomStorePackageSettingsDetailComponent,
    EcomStorePackageSettingsUpdateComponent,
    EcomStorePackageSettingsDeleteDialogComponent,
  ],
  entryComponents: [EcomStorePackageSettingsDeleteDialogComponent],
})
export class EcomStorePackageSettingsModule {}
