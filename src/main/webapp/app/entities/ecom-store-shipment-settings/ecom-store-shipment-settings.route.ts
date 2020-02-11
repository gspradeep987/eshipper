import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomStoreShipmentSettings, EcomStoreShipmentSettings } from 'app/shared/model/ecom-store-shipment-settings.model';
import { EcomStoreShipmentSettingsService } from './ecom-store-shipment-settings.service';
import { EcomStoreShipmentSettingsComponent } from './ecom-store-shipment-settings.component';
import { EcomStoreShipmentSettingsDetailComponent } from './ecom-store-shipment-settings-detail.component';
import { EcomStoreShipmentSettingsUpdateComponent } from './ecom-store-shipment-settings-update.component';

@Injectable({ providedIn: 'root' })
export class EcomStoreShipmentSettingsResolve implements Resolve<IEcomStoreShipmentSettings> {
  constructor(private service: EcomStoreShipmentSettingsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomStoreShipmentSettings> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomStoreShipmentSettings: HttpResponse<EcomStoreShipmentSettings>) => {
          if (ecomStoreShipmentSettings.body) {
            return of(ecomStoreShipmentSettings.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomStoreShipmentSettings());
  }
}

export const ecomStoreShipmentSettingsRoute: Routes = [
  {
    path: '',
    component: EcomStoreShipmentSettingsComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStoreShipmentSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EcomStoreShipmentSettingsDetailComponent,
    resolve: {
      ecomStoreShipmentSettings: EcomStoreShipmentSettingsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStoreShipmentSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EcomStoreShipmentSettingsUpdateComponent,
    resolve: {
      ecomStoreShipmentSettings: EcomStoreShipmentSettingsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStoreShipmentSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EcomStoreShipmentSettingsUpdateComponent,
    resolve: {
      ecomStoreShipmentSettings: EcomStoreShipmentSettingsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStoreShipmentSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
