import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomMarkupSecondary, EcomMarkupSecondary } from 'app/shared/model/ecom-markup-secondary.model';
import { EcomMarkupSecondaryService } from './ecom-markup-secondary.service';
import { EcomMarkupSecondaryComponent } from './ecom-markup-secondary.component';
import { EcomMarkupSecondaryDetailComponent } from './ecom-markup-secondary-detail.component';
import { EcomMarkupSecondaryUpdateComponent } from './ecom-markup-secondary-update.component';

@Injectable({ providedIn: 'root' })
export class EcomMarkupSecondaryResolve implements Resolve<IEcomMarkupSecondary> {
  constructor(private service: EcomMarkupSecondaryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomMarkupSecondary> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomMarkupSecondary: HttpResponse<EcomMarkupSecondary>) => {
          if (ecomMarkupSecondary.body) {
            return of(ecomMarkupSecondary.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomMarkupSecondary());
  }
}

export const ecomMarkupSecondaryRoute: Routes = [
  {
    path: '',
    component: EcomMarkupSecondaryComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMarkupSecondary.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EcomMarkupSecondaryDetailComponent,
    resolve: {
      ecomMarkupSecondary: EcomMarkupSecondaryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMarkupSecondary.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EcomMarkupSecondaryUpdateComponent,
    resolve: {
      ecomMarkupSecondary: EcomMarkupSecondaryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMarkupSecondary.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EcomMarkupSecondaryUpdateComponent,
    resolve: {
      ecomMarkupSecondary: EcomMarkupSecondaryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMarkupSecondary.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
