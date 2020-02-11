import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomStoreAddress, EcomStoreAddress } from 'app/shared/model/ecom-store-address.model';
import { EcomStoreAddressService } from './ecom-store-address.service';
import { EcomStoreAddressComponent } from './ecom-store-address.component';
import { EcomStoreAddressDetailComponent } from './ecom-store-address-detail.component';
import { EcomStoreAddressUpdateComponent } from './ecom-store-address-update.component';

@Injectable({ providedIn: 'root' })
export class EcomStoreAddressResolve implements Resolve<IEcomStoreAddress> {
  constructor(private service: EcomStoreAddressService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomStoreAddress> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomStoreAddress: HttpResponse<EcomStoreAddress>) => {
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

export const ecomStoreAddressRoute: Routes = [
  {
    path: '',
    component: EcomStoreAddressComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStoreAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EcomStoreAddressDetailComponent,
    resolve: {
      ecomStoreAddress: EcomStoreAddressResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStoreAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EcomStoreAddressUpdateComponent,
    resolve: {
      ecomStoreAddress: EcomStoreAddressResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStoreAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EcomStoreAddressUpdateComponent,
    resolve: {
      ecomStoreAddress: EcomStoreAddressResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomStoreAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
