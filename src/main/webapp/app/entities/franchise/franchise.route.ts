import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFranchise, Franchise } from 'app/shared/model/franchise.model';
import { FranchiseService } from './franchise.service';
import { FranchiseComponent } from './franchise.component';
import { FranchiseDetailComponent } from './franchise-detail.component';
import { FranchiseUpdateComponent } from './franchise-update.component';

@Injectable({ providedIn: 'root' })
export class FranchiseResolve implements Resolve<IFranchise> {
  constructor(private service: FranchiseService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFranchise> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((franchise: HttpResponse<Franchise>) => {
          if (franchise.body) {
            return of(franchise.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Franchise());
  }
}

export const franchiseRoute: Routes = [
  {
    path: '',
    component: FranchiseComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.franchise.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FranchiseDetailComponent,
    resolve: {
      franchise: FranchiseResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.franchise.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FranchiseUpdateComponent,
    resolve: {
      franchise: FranchiseResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.franchise.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FranchiseUpdateComponent,
    resolve: {
      franchise: FranchiseResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.franchise.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
