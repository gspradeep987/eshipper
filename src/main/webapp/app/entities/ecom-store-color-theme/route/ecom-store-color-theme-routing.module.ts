import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EcomStoreColorThemeComponent } from '../list/ecom-store-color-theme.component';
import { EcomStoreColorThemeDetailComponent } from '../detail/ecom-store-color-theme-detail.component';
import { EcomStoreColorThemeUpdateComponent } from '../update/ecom-store-color-theme-update.component';
import { EcomStoreColorThemeRoutingResolveService } from './ecom-store-color-theme-routing-resolve.service';

const ecomStoreColorThemeRoute: Routes = [
  {
    path: '',
    component: EcomStoreColorThemeComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomStoreColorThemeDetailComponent,
    resolve: {
      ecomStoreColorTheme: EcomStoreColorThemeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomStoreColorThemeUpdateComponent,
    resolve: {
      ecomStoreColorTheme: EcomStoreColorThemeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomStoreColorThemeUpdateComponent,
    resolve: {
      ecomStoreColorTheme: EcomStoreColorThemeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ecomStoreColorThemeRoute)],
  exports: [RouterModule],
})
export class EcomStoreColorThemeRoutingModule {}
