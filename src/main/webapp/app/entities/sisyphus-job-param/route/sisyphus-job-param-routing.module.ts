import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SisyphusJobParamComponent } from '../list/sisyphus-job-param.component';
import { SisyphusJobParamDetailComponent } from '../detail/sisyphus-job-param-detail.component';
import { SisyphusJobParamUpdateComponent } from '../update/sisyphus-job-param-update.component';
import { SisyphusJobParamRoutingResolveService } from './sisyphus-job-param-routing-resolve.service';

const sisyphusJobParamRoute: Routes = [
  {
    path: '',
    component: SisyphusJobParamComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SisyphusJobParamDetailComponent,
    resolve: {
      sisyphusJobParam: SisyphusJobParamRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SisyphusJobParamUpdateComponent,
    resolve: {
      sisyphusJobParam: SisyphusJobParamRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SisyphusJobParamUpdateComponent,
    resolve: {
      sisyphusJobParam: SisyphusJobParamRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sisyphusJobParamRoute)],
  exports: [RouterModule],
})
export class SisyphusJobParamRoutingModule {}
