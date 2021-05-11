import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { EcomMailTemplateComponent } from '../list/ecom-mail-template.component';
import { EcomMailTemplateDetailComponent } from '../detail/ecom-mail-template-detail.component';
import { EcomMailTemplateUpdateComponent } from '../update/ecom-mail-template-update.component';
import { EcomMailTemplateRoutingResolveService } from './ecom-mail-template-routing-resolve.service';

const ecomMailTemplateRoute: Routes = [
  {
    path: '',
    component: EcomMailTemplateComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomMailTemplateDetailComponent,
    resolve: {
      ecomMailTemplate: EcomMailTemplateRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomMailTemplateUpdateComponent,
    resolve: {
      ecomMailTemplate: EcomMailTemplateRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomMailTemplateUpdateComponent,
    resolve: {
      ecomMailTemplate: EcomMailTemplateRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ecomMailTemplateRoute)],
  exports: [RouterModule],
})
export class EcomMailTemplateRoutingModule {}
