import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomStore, EcomStore } from 'app/shared/model/ecom-store.model';
import { EcomStoreService } from './ecom-store.service';
import { EcomStoreComponent } from './ecom-store.component';
import { EcomStoreDetailComponent } from './ecom-store-detail.component';
import { EcomStoreUpdateComponent } from './ecom-store-update.component';

@Injectable({ providedIn: 'root' })
export class EcomStoreResolve implements Resolve<IEcomStore> {
  constructor(private service: EcomStoreService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomStore> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomStore: HttpResponse<EcomStore>) => {
          if (ecomStore.body) {
            return of(ecomStore.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomStore());
  }
}

export const ecomStoreRoute: Routes = [
  {
    path: '',
    component: EcomStoreComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomStore.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomStoreDetailComponent,
    resolve: {
      ecomStore: EcomStoreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomStore.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomStoreUpdateComponent,
    resolve: {
      ecomStore: EcomStoreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomStore.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomStoreUpdateComponent,
    resolve: {
      ecomStore: EcomStoreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomStore.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
