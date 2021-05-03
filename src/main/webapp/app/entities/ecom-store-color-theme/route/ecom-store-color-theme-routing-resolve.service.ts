import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEcomStoreColorTheme, EcomStoreColorTheme } from '../ecom-store-color-theme.model';
import { EcomStoreColorThemeService } from '../service/ecom-store-color-theme.service';

@Injectable({ providedIn: 'root' })
export class EcomStoreColorThemeRoutingResolveService implements Resolve<IEcomStoreColorTheme> {
  constructor(protected service: EcomStoreColorThemeService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomStoreColorTheme> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ecomStoreColorTheme: HttpResponse<EcomStoreColorTheme>) => {
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
