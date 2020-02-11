import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomStorePackageSettings, EcomStorePackageSettings } from 'app/shared/model/ecom-store-package-settings.model';
import { EcomStorePackageSettingsService } from './ecom-store-package-settings.service';
import { EcomStorePackageSettingsComponent } from './ecom-store-package-settings.component';
import { EcomStorePackageSettingsDetailComponent } from './ecom-store-package-settings-detail.component';
import { EcomStorePackageSettingsUpdateComponent } from './ecom-store-package-settings-update.component';

@Injectable({ providedIn: 'root' })
export class EcomStorePackageSettingsResolve implements Resolve<IEcomStorePackageSettings> {
  constructor(private service: EcomStorePackageSettingsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomStorePackageSettings> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomStorePackageSettings: HttpResponse<EcomStorePackageSettings>) => {
          if (ecomStorePackageSettings.body) {
            return of(ecomStorePackageSettings.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomStorePackageSettings());
  }
}

export const ecomStorePackageSettingsRoute: Routes = [
  {
    path: '',
    component: EcomStorePackageSettingsComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStorePackageSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EcomStorePackageSettingsDetailComponent,
    resolve: {
      ecomStorePackageSettings: EcomStorePackageSettingsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStorePackageSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EcomStorePackageSettingsUpdateComponent,
    resolve: {
      ecomStorePackageSettings: EcomStorePackageSettingsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStorePackageSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EcomStorePackageSettingsUpdateComponent,
    resolve: {
      ecomStorePackageSettings: EcomStorePackageSettingsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStorePackageSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
