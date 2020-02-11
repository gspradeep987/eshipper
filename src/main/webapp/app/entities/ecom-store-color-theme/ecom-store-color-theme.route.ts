import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomStoreColorTheme, EcomStoreColorTheme } from 'app/shared/model/ecom-store-color-theme.model';
import { EcomStoreColorThemeService } from './ecom-store-color-theme.service';
import { EcomStoreColorThemeComponent } from './ecom-store-color-theme.component';
import { EcomStoreColorThemeDetailComponent } from './ecom-store-color-theme-detail.component';
import { EcomStoreColorThemeUpdateComponent } from './ecom-store-color-theme-update.component';

@Injectable({ providedIn: 'root' })
export class EcomStoreColorThemeResolve implements Resolve<IEcomStoreColorTheme> {
  constructor(private service: EcomStoreColorThemeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomStoreColorTheme> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomStoreColorTheme: HttpResponse<EcomStoreColorTheme>) => {
          if (ecomStoreColorTheme.body) {
            return of(ecomStoreColorTheme.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomStoreColorTheme());
  }
}

export const ecomStoreColorThemeRoute: Routes = [
  {
    path: '',
    component: EcomStoreColorThemeComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStoreColorTheme.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EcomStoreColorThemeDetailComponent,
    resolve: {
      ecomStoreColorTheme: EcomStoreColorThemeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStoreColorTheme.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EcomStoreColorThemeUpdateComponent,
    resolve: {
      ecomStoreColorTheme: EcomStoreColorThemeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStoreColorTheme.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EcomStoreColorThemeUpdateComponent,
    resolve: {
      ecomStoreColorTheme: EcomStoreColorThemeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStoreColorTheme.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
