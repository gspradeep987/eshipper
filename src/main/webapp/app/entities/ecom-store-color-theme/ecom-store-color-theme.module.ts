import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { EcomStoreColorThemeComponent } from './list/ecom-store-color-theme.component';
import { EcomStoreColorThemeDetailComponent } from './detail/ecom-store-color-theme-detail.component';
import { EcomStoreColorThemeUpdateComponent } from './update/ecom-store-color-theme-update.component';
import { EcomStoreColorThemeDeleteDialogComponent } from './delete/ecom-store-color-theme-delete-dialog.component';
import { EcomStoreColorThemeRoutingModule } from './route/ecom-store-color-theme-routing.module';

@NgModule({
  imports: [SharedModule, EcomStoreColorThemeRoutingModule],
  declarations: [
    EcomStoreColorThemeComponent,
    EcomStoreColorThemeDetailComponent,
    EcomStoreColorThemeUpdateComponent,
    EcomStoreColorThemeDeleteDialogComponent,
  ],
  entryComponents: [EcomStoreColorThemeDeleteDialogComponent],
})
export class EcomStoreColorThemeModule {}
