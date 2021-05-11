import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEcomStoreShipmentSettings, EcomStoreShipmentSettings } from '../ecom-store-shipment-settings.model';
import { EcomStoreShipmentSettingsService } from '../service/ecom-store-shipment-settings.service';

@Injectable({ providedIn: 'root' })
export class EcomStoreShipmentSettingsRoutingResolveService implements Resolve<IEcomStoreShipmentSettings> {
  constructor(protected service: EcomStoreShipmentSettingsService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomStoreShipmentSettings> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ecomStoreShipmentSettings: HttpResponse<EcomStoreShipmentSettings>) => {
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
