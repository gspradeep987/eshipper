import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IShippingAddress, ShippingAddress } from '../shipping-address.model';
import { ShippingAddressService } from '../service/shipping-address.service';

@Injectable({ providedIn: 'root' })
export class ShippingAddressRoutingResolveService implements Resolve<IShippingAddress> {
  constructor(protected service: ShippingAddressService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IShippingAddress> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((shippingAddress: HttpResponse<ShippingAddress>) => {
          if (shippingAddress.body) {
            return of(shippingAddress.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ShippingAddress());
  }
}
