import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEcomStore, EcomStore } from '../ecom-store.model';
import { EcomStoreService } from '../service/ecom-store.service';

@Injectable({ providedIn: 'root' })
export class EcomStoreRoutingResolveService implements Resolve<IEcomStore> {
  constructor(protected service: EcomStoreService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomStore> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ecomStore: HttpResponse<EcomStore>) => {
          if (ecomStore.body) {
            return of(ecomStore.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomStore());
  }
}
