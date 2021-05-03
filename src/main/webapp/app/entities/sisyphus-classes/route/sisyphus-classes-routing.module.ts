import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SisyphusClassesComponent } from '../list/sisyphus-classes.component';
import { SisyphusClassesDetailComponent } from '../detail/sisyphus-classes-detail.component';
import { SisyphusClassesUpdateComponent } from '../update/sisyphus-classes-update.component';
import { SisyphusClassesRoutingResolveService } from './sisyphus-classes-routing-resolve.service';

const sisyphusClassesRoute: Routes = [
  {
    path: '',
    component: SisyphusClassesComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SisyphusClassesDetailComponent,
    resolve: {
      sisyphusClasses: SisyphusClassesRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SisyphusClassesUpdateComponent,
    resolve: {
      sisyphusClasses: SisyphusClassesRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SisyphusClassesUpdateComponent,
    resolve: {
      sisyphusClasses: SisyphusClassesRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(sisyphusClassesRoute)],
  exports: [RouterModule],
})
export class SisyphusClassesRoutingModule {}
