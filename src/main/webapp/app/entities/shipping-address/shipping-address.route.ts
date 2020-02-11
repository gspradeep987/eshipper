import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IShippingAddress, ShippingAddress } from 'app/shared/model/shipping-address.model';
import { ShippingAddressService } from './shipping-address.service';
import { ShippingAddressComponent } from './shipping-address.component';
import { ShippingAddressDetailComponent } from './shipping-address-detail.component';
import { ShippingAddressUpdateComponent } from './shipping-address-update.component';

@Injectable({ providedIn: 'root' })
export class ShippingAddressResolve implements Resolve<IShippingAddress> {
  constructor(private service: ShippingAddressService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IShippingAddress> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((shippingAddress: HttpResponse<ShippingAddress>) => {
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

export const shippingAddressRoute: Routes = [
  {
    path: '',
    component: ShippingAddressComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.shippingAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ShippingAddressDetailComponent,
    resolve: {
      shippingAddress: ShippingAddressResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.shippingAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ShippingAddressUpdateComponent,
    resolve: {
      shippingAddress: ShippingAddressResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.shippingAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ShippingAddressUpdateComponent,
    resolve: {
      shippingAddress: ShippingAddressResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.shippingAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
