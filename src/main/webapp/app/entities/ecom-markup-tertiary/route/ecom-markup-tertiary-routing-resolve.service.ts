import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEcomMarkupTertiary, EcomMarkupTertiary } from '../ecom-markup-tertiary.model';
import { EcomMarkupTertiaryService } from '../service/ecom-markup-tertiary.service';

@Injectable({ providedIn: 'root' })
export class EcomMarkupTertiaryRoutingResolveService implements Resolve<IEcomMarkupTertiary> {
  constructor(protected service: EcomMarkupTertiaryService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomMarkupTertiary> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ecomMarkupTertiary: HttpResponse<EcomMarkupTertiary>) => {
          if (ecomMarkupTertiary.body) {
            return of(ecomMarkupTertiary.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomMarkupTertiary());
  }
}
