import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomMarkupTertiary, EcomMarkupTertiary } from 'app/shared/model/ecom-markup-tertiary.model';
import { EcomMarkupTertiaryService } from './ecom-markup-tertiary.service';
import { EcomMarkupTertiaryComponent } from './ecom-markup-tertiary.component';
import { EcomMarkupTertiaryDetailComponent } from './ecom-markup-tertiary-detail.component';
import { EcomMarkupTertiaryUpdateComponent } from './ecom-markup-tertiary-update.component';

@Injectable({ providedIn: 'root' })
export class EcomMarkupTertiaryResolve implements Resolve<IEcomMarkupTertiary> {
  constructor(private service: EcomMarkupTertiaryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomMarkupTertiary> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomMarkupTertiary: HttpResponse<EcomMarkupTertiary>) => {
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

export const ecomMarkupTertiaryRoute: Routes = [
  {
    path: '',
    component: EcomMarkupTertiaryComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMarkupTertiary.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EcomMarkupTertiaryDetailComponent,
    resolve: {
      ecomMarkupTertiary: EcomMarkupTertiaryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMarkupTertiary.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EcomMarkupTertiaryUpdateComponent,
    resolve: {
      ecomMarkupTertiary: EcomMarkupTertiaryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMarkupTertiary.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EcomMarkupTertiaryUpdateComponent,
    resolve: {
      ecomMarkupTertiary: EcomMarkupTertiaryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMarkupTertiary.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
