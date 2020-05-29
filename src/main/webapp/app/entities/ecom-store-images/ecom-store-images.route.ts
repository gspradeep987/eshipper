import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomStoreImages, EcomStoreImages } from 'app/shared/model/ecom-store-images.model';
import { EcomStoreImagesService } from './ecom-store-images.service';
import { EcomStoreImagesComponent } from './ecom-store-images.component';
import { EcomStoreImagesDetailComponent } from './ecom-store-images-detail.component';
import { EcomStoreImagesUpdateComponent } from './ecom-store-images-update.component';

@Injectable({ providedIn: 'root' })
export class EcomStoreImagesResolve implements Resolve<IEcomStoreImages> {
  constructor(private service: EcomStoreImagesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomStoreImages> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomStoreImages: HttpResponse<EcomStoreImages>) => {
          if (ecomStoreImages.body) {
            return of(ecomStoreImages.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomStoreImages());
  }
}

export const ecomStoreImagesRoute: Routes = [
  {
    path: '',
    component: EcomStoreImagesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomStoreImages.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomStoreImagesDetailComponent,
    resolve: {
      ecomStoreImages: EcomStoreImagesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomStoreImages.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomStoreImagesUpdateComponent,
    resolve: {
      ecomStoreImages: EcomStoreImagesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomStoreImages.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomStoreImagesUpdateComponent,
    resolve: {
      ecomStoreImages: EcomStoreImagesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomStoreImages.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
