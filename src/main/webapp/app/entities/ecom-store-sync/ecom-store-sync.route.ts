import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomStoreSync, EcomStoreSync } from 'app/shared/model/ecom-store-sync.model';
import { EcomStoreSyncService } from './ecom-store-sync.service';
import { EcomStoreSyncComponent } from './ecom-store-sync.component';
import { EcomStoreSyncDetailComponent } from './ecom-store-sync-detail.component';
import { EcomStoreSyncUpdateComponent } from './ecom-store-sync-update.component';

@Injectable({ providedIn: 'root' })
export class EcomStoreSyncResolve implements Resolve<IEcomStoreSync> {
  constructor(private service: EcomStoreSyncService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomStoreSync> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomStoreSync: HttpResponse<EcomStoreSync>) => {
          if (ecomStoreSync.body) {
            return of(ecomStoreSync.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomStoreSync());
  }
}

export const ecomStoreSyncRoute: Routes = [
  {
    path: '',
    component: EcomStoreSyncComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomStoreSync.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EcomStoreSyncDetailComponent,
    resolve: {
      ecomStoreSync: EcomStoreSyncResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomStoreSync.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EcomStoreSyncUpdateComponent,
    resolve: {
      ecomStoreSync: EcomStoreSyncResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomStoreSync.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EcomStoreSyncUpdateComponent,
    resolve: {
      ecomStoreSync: EcomStoreSyncResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomStoreSync.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
