import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUser10, User10 } from 'app/shared/model/user-10.model';
import { User10Service } from './user-10.service';
import { User10Component } from './user-10.component';
import { User10DetailComponent } from './user-10-detail.component';
import { User10UpdateComponent } from './user-10-update.component';

@Injectable({ providedIn: 'root' })
export class User10Resolve implements Resolve<IUser10> {
  constructor(private service: User10Service, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUser10> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((user10: HttpResponse<User10>) => {
          if (user10.body) {
            return of(user10.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new User10());
  }
}

export const user10Route: Routes = [
  {
    path: '',
    component: User10Component,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.user10.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: User10DetailComponent,
    resolve: {
      user10: User10Resolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.user10.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: User10UpdateComponent,
    resolve: {
      user10: User10Resolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.user10.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: User10UpdateComponent,
    resolve: {
      user10: User10Resolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.user10.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
