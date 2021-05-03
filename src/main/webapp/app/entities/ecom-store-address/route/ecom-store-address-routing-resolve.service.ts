import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEcomStoreAddress, EcomStoreAddress } from '../ecom-store-address.model';
import { EcomStoreAddressService } from '../service/ecom-store-address.service';

@Injectable({ providedIn: 'root' })
export class EcomStoreAddressRoutingResolveService implements Resolve<IEcomStoreAddress> {
  constructor(protected service: EcomStoreAddressService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomStoreAddress> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ecomStoreAddress: HttpResponse<EcomStoreAddress>) => {
          if (ecomStoreAddress.body) {
            return of(ecomStoreAddress.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomStoreAddress());
  }
}
