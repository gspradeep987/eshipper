import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomProduct, EcomProduct } from 'app/shared/model/ecom-product.model';
import { EcomProductService } from './ecom-product.service';
import { EcomProductComponent } from './ecom-product.component';
import { EcomProductDetailComponent } from './ecom-product-detail.component';
import { EcomProductUpdateComponent } from './ecom-product-update.component';

@Injectable({ providedIn: 'root' })
export class EcomProductResolve implements Resolve<IEcomProduct> {
  constructor(private service: EcomProductService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomProduct> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomProduct: HttpResponse<EcomProduct>) => {
          if (ecomProduct.body) {
            return of(ecomProduct.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomProduct());
  }
}

export const ecomProductRoute: Routes = [
  {
    path: '',
    component: EcomProductComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EcomProductDetailComponent,
    resolve: {
      ecomProduct: EcomProductResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EcomProductUpdateComponent,
    resolve: {
      ecomProduct: EcomProductResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EcomProductUpdateComponent,
    resolve: {
      ecomProduct: EcomProductResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
