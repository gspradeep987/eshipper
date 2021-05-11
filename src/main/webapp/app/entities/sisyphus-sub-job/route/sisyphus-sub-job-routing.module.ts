import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SisyphusSubJobComponent } from '../list/sisyphus-sub-job.component';
import { SisyphusSubJobDetailComponent } from '../detail/sisyphus-sub-job-detail.component';
import { SisyphusSubJobUpdateComponent } from '../update/sisyphus-sub-job-update.component';
import { SisyphusSubJobRoutingResolveService } from './sisyphus-sub-job-routing-resolve.service';

const sisyphusSubJobRoute: Routes = [
  {
    path: '',
    component: SisyphusSubJobComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SisyphusSubJobDetailComponent,
    resolve: {
      sisyphusSubJob: SisyphusSubJobRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SisyphusSubJobUpdateComponent,
    resolve: {
      sisyphusSubJob: SisyphusSubJobRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SisyphusSubJobUpdateComponent,
    resolve: {
      sisyphusSubJob: SisyphusSubJobRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sisyphusSubJobRoute)],
  exports: [RouterModule],
})
export class SisyphusSubJobRoutingModule {}
