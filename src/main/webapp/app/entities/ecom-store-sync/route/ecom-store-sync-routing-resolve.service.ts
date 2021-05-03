import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEcomStoreSync, EcomStoreSync } from '../ecom-store-sync.model';
import { EcomStoreSyncService } from '../service/ecom-store-sync.service';

@Injectable({ providedIn: 'root' })
export class EcomStoreSyncRoutingResolveService implements Resolve<IEcomStoreSync> {
  constructor(protected service: EcomStoreSyncService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomStoreSync> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ecomStoreSync: HttpResponse<EcomStoreSync>) => {
          if (ecomStoreSync.body) {
            return of(ecomStoreSync.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomStoreSync());
  }
}
