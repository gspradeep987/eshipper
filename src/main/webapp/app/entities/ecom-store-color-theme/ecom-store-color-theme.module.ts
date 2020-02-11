import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EcomStoreColorThemeComponent } from './ecom-store-color-theme.component';
import { EcomStoreColorThemeDetailComponent } from './ecom-store-color-theme-detail.component';
import { EcomStoreColorThemeUpdateComponent } from './ecom-store-color-theme-update.component';
import { EcomStoreColorThemeDeleteDialogComponent } from './ecom-store-color-theme-delete-dialog.component';
import { ecomStoreColorThemeRoute } from './ecom-store-color-theme.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ecomStoreColorThemeRoute)],
  declarations: [
    EcomStoreColorThemeComponent,
    EcomStoreColorThemeDetailComponent,
    EcomStoreColorThemeUpdateComponent,
    EcomStoreColorThemeDeleteDialogComponent
  ],
  entryComponents: [EcomStoreColorThemeDeleteDialogComponent]
})
export class EshipperEcomStoreColorThemeModule {}
