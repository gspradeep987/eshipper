import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EcomMarkupTertiaryComponent } from '../list/ecom-markup-tertiary.component';
import { EcomMarkupTertiaryDetailComponent } from '../detail/ecom-markup-tertiary-detail.component';
import { EcomMarkupTertiaryUpdateComponent } from '../update/ecom-markup-tertiary-update.component';
import { EcomMarkupTertiaryRoutingResolveService } from './ecom-markup-tertiary-routing-resolve.service';

const ecomMarkupTertiaryRoute: Routes = [
  {
    path: '',
    component: EcomMarkupTertiaryComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomMarkupTertiaryDetailComponent,
    resolve: {
      ecomMarkupTertiary: EcomMarkupTertiaryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomMarkupTertiaryUpdateComponent,
    resolve: {
      ecomMarkupTertiary: EcomMarkupTertiaryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomMarkupTertiaryUpdateComponent,
    resolve: {
      ecomMarkupTertiary: EcomMarkupTertiaryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ecomMarkupTertiaryRoute)],
  exports: [RouterModule],
})
export class EcomMarkupTertiaryRoutingModule {}
