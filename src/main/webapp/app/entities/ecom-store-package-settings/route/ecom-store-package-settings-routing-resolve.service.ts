import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEcomStorePackageSettings, EcomStorePackageSettings } from '../ecom-store-package-settings.model';
import { EcomStorePackageSettingsService } from '../service/ecom-store-package-settings.service';

@Injectable({ providedIn: 'root' })
export class EcomStorePackageSettingsRoutingResolveService implements Resolve<IEcomStorePackageSettings> {
  constructor(protected service: EcomStorePackageSettingsService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomStorePackageSettings> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ecomStorePackageSettings: HttpResponse<EcomStorePackageSettings>) => {
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
