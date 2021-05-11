import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SisyphusJobTypeComponent } from '../list/sisyphus-job-type.component';
import { SisyphusJobTypeDetailComponent } from '../detail/sisyphus-job-type-detail.component';
import { SisyphusJobTypeUpdateComponent } from '../update/sisyphus-job-type-update.component';
import { SisyphusJobTypeRoutingResolveService } from './sisyphus-job-type-routing-resolve.service';

const sisyphusJobTypeRoute: Routes = [
  {
    path: '',
    component: SisyphusJobTypeComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SisyphusJobTypeDetailComponent,
    resolve: {
      sisyphusJobType: SisyphusJobTypeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SisyphusJobTypeUpdateComponent,
    resolve: {
      sisyphusJobType: SisyphusJobTypeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SisyphusJobTypeUpdateComponent,
    resolve: {
      sisyphusJobType: SisyphusJobTypeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sisyphusJobTypeRoute)],
  exports: [RouterModule],
})
export class SisyphusJobTypeRoutingModule {}
