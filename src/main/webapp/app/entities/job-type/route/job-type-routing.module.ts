import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { JobTypeComponent } from '../list/job-type.component';
import { JobTypeDetailComponent } from '../detail/job-type-detail.component';
import { JobTypeUpdateComponent } from '../update/job-type-update.component';
import { JobTypeRoutingResolveService } from './job-type-routing-resolve.service';

const jobTypeRoute: Routes = [
  {
    path: '',
    component: JobTypeComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: JobTypeDetailComponent,
    resolve: {
      jobType: JobTypeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: JobTypeUpdateComponent,
    resolve: {
      jobType: JobTypeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: JobTypeUpdateComponent,
    resolve: {
      jobType: JobTypeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(jobTypeRoute)],
  exports: [RouterModule],
})
export class JobTypeRoutingModule {}
