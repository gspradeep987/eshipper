import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICarrierSettings, CarrierSettings } from 'app/shared/model/carrier-settings.model';
import { CarrierSettingsService } from './carrier-settings.service';
import { CarrierSettingsComponent } from './carrier-settings.component';
import { CarrierSettingsDetailComponent } from './carrier-settings-detail.component';
import { CarrierSettingsUpdateComponent } from './carrier-settings-update.component';

@Injectable({ providedIn: 'root' })
export class CarrierSettingsResolve implements Resolve<ICarrierSettings> {
  constructor(private service: CarrierSettingsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICarrierSettings> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((carrierSettings: HttpResponse<CarrierSettings>) => {
          if (carrierSettings.body) {
            return of(carrierSettings.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CarrierSettings());
  }
}

export const carrierSettingsRoute: Routes = [
  {
    path: '',
    component: CarrierSettingsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.carrierSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CarrierSettingsDetailComponent,
    resolve: {
      carrierSettings: CarrierSettingsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.carrierSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CarrierSettingsUpdateComponent,
    resolve: {
      carrierSettings: CarrierSettingsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.carrierSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CarrierSettingsUpdateComponent,
    resolve: {
      carrierSettings: CarrierSettingsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.carrierSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
