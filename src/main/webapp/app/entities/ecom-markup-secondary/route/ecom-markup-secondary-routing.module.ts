import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EcomMarkupSecondaryComponent } from '../list/ecom-markup-secondary.component';
import { EcomMarkupSecondaryDetailComponent } from '../detail/ecom-markup-secondary-detail.component';
import { EcomMarkupSecondaryUpdateComponent } from '../update/ecom-markup-secondary-update.component';
import { EcomMarkupSecondaryRoutingResolveService } from './ecom-markup-secondary-routing-resolve.service';

const ecomMarkupSecondaryRoute: Routes = [
  {
    path: '',
    component: EcomMarkupSecondaryComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomMarkupSecondaryDetailComponent,
    resolve: {
      ecomMarkupSecondary: EcomMarkupSecondaryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomMarkupSecondaryUpdateComponent,
    resolve: {
      ecomMarkupSecondary: EcomMarkupSecondaryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomMarkupSecondaryUpdateComponent,
    resolve: {
      ecomMarkupSecondary: EcomMarkupSecondaryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ecomMarkupSecondaryRoute)],
  exports: [RouterModule],
})
export class EcomMarkupSecondaryRoutingModule {}
