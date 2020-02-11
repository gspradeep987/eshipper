import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomMarkupQuaternary, EcomMarkupQuaternary } from 'app/shared/model/ecom-markup-quaternary.model';
import { EcomMarkupQuaternaryService } from './ecom-markup-quaternary.service';
import { EcomMarkupQuaternaryComponent } from './ecom-markup-quaternary.component';
import { EcomMarkupQuaternaryDetailComponent } from './ecom-markup-quaternary-detail.component';
import { EcomMarkupQuaternaryUpdateComponent } from './ecom-markup-quaternary-update.component';

@Injectable({ providedIn: 'root' })
export class EcomMarkupQuaternaryResolve implements Resolve<IEcomMarkupQuaternary> {
  constructor(private service: EcomMarkupQuaternaryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomMarkupQuaternary> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomMarkupQuaternary: HttpResponse<EcomMarkupQuaternary>) => {
          if (ecomMarkupQuaternary.body) {
            return of(ecomMarkupQuaternary.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomMarkupQuaternary());
  }
}

export const ecomMarkupQuaternaryRoute: Routes = [
  {
    path: '',
    component: EcomMarkupQuaternaryComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMarkupQuaternary.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EcomMarkupQuaternaryDetailComponent,
    resolve: {
      ecomMarkupQuaternary: EcomMarkupQuaternaryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMarkupQuaternary.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EcomMarkupQuaternaryUpdateComponent,
    resolve: {
      ecomMarkupQuaternary: EcomMarkupQuaternaryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMarkupQuaternary.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EcomMarkupQuaternaryUpdateComponent,
    resolve: {
      ecomMarkupQuaternary: EcomMarkupQuaternaryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMarkupQuaternary.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
