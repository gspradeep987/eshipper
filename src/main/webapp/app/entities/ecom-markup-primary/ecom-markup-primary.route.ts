import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomMarkupPrimary, EcomMarkupPrimary } from 'app/shared/model/ecom-markup-primary.model';
import { EcomMarkupPrimaryService } from './ecom-markup-primary.service';
import { EcomMarkupPrimaryComponent } from './ecom-markup-primary.component';
import { EcomMarkupPrimaryDetailComponent } from './ecom-markup-primary-detail.component';
import { EcomMarkupPrimaryUpdateComponent } from './ecom-markup-primary-update.component';

@Injectable({ providedIn: 'root' })
export class EcomMarkupPrimaryResolve implements Resolve<IEcomMarkupPrimary> {
  constructor(private service: EcomMarkupPrimaryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomMarkupPrimary> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomMarkupPrimary: HttpResponse<EcomMarkupPrimary>) => {
          if (ecomMarkupPrimary.body) {
            return of(ecomMarkupPrimary.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomMarkupPrimary());
  }
}

export const ecomMarkupPrimaryRoute: Routes = [
  {
    path: '',
    component: EcomMarkupPrimaryComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMarkupPrimary.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EcomMarkupPrimaryDetailComponent,
    resolve: {
      ecomMarkupPrimary: EcomMarkupPrimaryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMarkupPrimary.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EcomMarkupPrimaryUpdateComponent,
    resolve: {
      ecomMarkupPrimary: EcomMarkupPrimaryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMarkupPrimary.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EcomMarkupPrimaryUpdateComponent,
    resolve: {
      ecomMarkupPrimary: EcomMarkupPrimaryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMarkupPrimary.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
