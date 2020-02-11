import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomStoreMarkup, EcomStoreMarkup } from 'app/shared/model/ecom-store-markup.model';
import { EcomStoreMarkupService } from './ecom-store-markup.service';
import { EcomStoreMarkupComponent } from './ecom-store-markup.component';
import { EcomStoreMarkupDetailComponent } from './ecom-store-markup-detail.component';
import { EcomStoreMarkupUpdateComponent } from './ecom-store-markup-update.component';

@Injectable({ providedIn: 'root' })
export class EcomStoreMarkupResolve implements Resolve<IEcomStoreMarkup> {
  constructor(private service: EcomStoreMarkupService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomStoreMarkup> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomStoreMarkup: HttpResponse<EcomStoreMarkup>) => {
          if (ecomStoreMarkup.body) {
            return of(ecomStoreMarkup.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomStoreMarkup());
  }
}

export const ecomStoreMarkupRoute: Routes = [
  {
    path: '',
    component: EcomStoreMarkupComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStoreMarkup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EcomStoreMarkupDetailComponent,
    resolve: {
      ecomStoreMarkup: EcomStoreMarkupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStoreMarkup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EcomStoreMarkupUpdateComponent,
    resolve: {
      ecomStoreMarkup: EcomStoreMarkupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStoreMarkup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EcomStoreMarkupUpdateComponent,
    resolve: {
      ecomStoreMarkup: EcomStoreMarkupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStoreMarkup.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
