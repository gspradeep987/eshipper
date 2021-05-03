import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EcomStorePackageSettingsComponent } from '../list/ecom-store-package-settings.component';
import { EcomStorePackageSettingsDetailComponent } from '../detail/ecom-store-package-settings-detail.component';
import { EcomStorePackageSettingsUpdateComponent } from '../update/ecom-store-package-settings-update.component';
import { EcomStorePackageSettingsRoutingResolveService } from './ecom-store-package-settings-routing-resolve.service';

const ecomStorePackageSettingsRoute: Routes = [
  {
    path: '',
    component: EcomStorePackageSettingsComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomStorePackageSettingsDetailComponent,
    resolve: {
      ecomStorePackageSettings: EcomStorePackageSettingsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomStorePackageSettingsUpdateComponent,
    resolve: {
      ecomStorePackageSettings: EcomStorePackageSettingsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomStorePackageSettingsUpdateComponent,
    resolve: {
      ecomStorePackageSettings: EcomStorePackageSettingsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ecomStorePackageSettingsRoute)],
  exports: [RouterModule],
})
export class EcomStorePackageSettingsRoutingModule {}
