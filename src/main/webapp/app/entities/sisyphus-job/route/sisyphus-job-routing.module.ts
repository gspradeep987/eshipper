import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SisyphusJobComponent } from '../list/sisyphus-job.component';
import { SisyphusJobDetailComponent } from '../detail/sisyphus-job-detail.component';
import { SisyphusJobUpdateComponent } from '../update/sisyphus-job-update.component';
import { SisyphusJobRoutingResolveService } from './sisyphus-job-routing-resolve.service';

const sisyphusJobRoute: Routes = [
  {
    path: '',
    component: SisyphusJobComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SisyphusJobDetailComponent,
    resolve: {
      sisyphusJob: SisyphusJobRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SisyphusJobUpdateComponent,
    resolve: {
      sisyphusJob: SisyphusJobRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SisyphusJobUpdateComponent,
    resolve: {
      sisyphusJob: SisyphusJobRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sisyphusJobRoute)],
  exports: [RouterModule],
})
export class SisyphusJobRoutingModule {}
