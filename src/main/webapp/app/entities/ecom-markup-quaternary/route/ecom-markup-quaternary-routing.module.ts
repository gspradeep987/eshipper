import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EcomMarkupQuaternaryComponent } from '../list/ecom-markup-quaternary.component';
import { EcomMarkupQuaternaryDetailComponent } from '../detail/ecom-markup-quaternary-detail.component';
import { EcomMarkupQuaternaryUpdateComponent } from '../update/ecom-markup-quaternary-update.component';
import { EcomMarkupQuaternaryRoutingResolveService } from './ecom-markup-quaternary-routing-resolve.service';

const ecomMarkupQuaternaryRoute: Routes = [
  {
    path: '',
    component: EcomMarkupQuaternaryComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomMarkupQuaternaryDetailComponent,
    resolve: {
      ecomMarkupQuaternary: EcomMarkupQuaternaryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomMarkupQuaternaryUpdateComponent,
    resolve: {
      ecomMarkupQuaternary: EcomMarkupQuaternaryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomMarkupQuaternaryUpdateComponent,
    resolve: {
      ecomMarkupQuaternary: EcomMarkupQuaternaryRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ecomMarkupQuaternaryRoute)],
  exports: [RouterModule],
})
export class EcomMarkupQuaternaryRoutingModule {}
