import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomProductImage, EcomProductImage } from 'app/shared/model/ecom-product-image.model';
import { EcomProductImageService } from './ecom-product-image.service';
import { EcomProductImageComponent } from './ecom-product-image.component';
import { EcomProductImageDetailComponent } from './ecom-product-image-detail.component';
import { EcomProductImageUpdateComponent } from './ecom-product-image-update.component';

@Injectable({ providedIn: 'root' })
export class EcomProductImageResolve implements Resolve<IEcomProductImage> {
  constructor(private service: EcomProductImageService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomProductImage> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomProductImage: HttpResponse<EcomProductImage>) => {
          if (ecomProductImage.body) {
            return of(ecomProductImage.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomProductImage());
  }
}

export const ecomProductImageRoute: Routes = [
  {
    path: '',
    component: EcomProductImageComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomProductImage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EcomProductImageDetailComponent,
    resolve: {
      ecomProductImage: EcomProductImageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomProductImage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EcomProductImageUpdateComponent,
    resolve: {
      ecomProductImage: EcomProductImageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomProductImage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EcomProductImageUpdateComponent,
    resolve: {
      ecomProductImage: EcomProductImageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomProductImage.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
