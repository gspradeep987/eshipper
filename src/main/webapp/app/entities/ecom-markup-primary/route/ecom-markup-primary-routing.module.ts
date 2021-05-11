import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EcomMarkupPrimaryComponent } from '../list/ecom-markup-primary.component';
import { EcomMarkupPrimaryDetailComponent } from '../detail/ecom-markup-primary-detail.component';
import { EcomMarkupPrimaryUpdateComponent } from '../update/ecom-markup-primary-update.component';
import { EcomMarkupPrimaryRoutingResolveService } from './ecom-markup-primary-routing-resolve.service';

const ecomMarkupPrimaryRoute: Routes = [
  {
    path: '',
    component: EcomMarkupPrimaryComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomMarkupPrimaryDetailComponent,
    resolve: {
      ecomMarkupPrimary: EcomMarkupPrimaryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomMarkupPrimaryUpdateComponent,
    resolve: {
      ecomMarkupPrimary: EcomMarkupPrimaryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomMarkupPrimaryUpdateComponent,
    resolve: {
      ecomMarkupPrimary: EcomMarkupPrimaryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ecomMarkupPrimaryRoute)],
  exports: [RouterModule],
})
export class EcomMarkupPrimaryRoutingModule {}
