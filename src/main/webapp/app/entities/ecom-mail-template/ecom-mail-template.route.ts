import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomMailTemplate, EcomMailTemplate } from 'app/shared/model/ecom-mail-template.model';
import { EcomMailTemplateService } from './ecom-mail-template.service';
import { EcomMailTemplateComponent } from './ecom-mail-template.component';
import { EcomMailTemplateDetailComponent } from './ecom-mail-template-detail.component';
import { EcomMailTemplateUpdateComponent } from './ecom-mail-template-update.component';

@Injectable({ providedIn: 'root' })
export class EcomMailTemplateResolve implements Resolve<IEcomMailTemplate> {
  constructor(private service: EcomMailTemplateService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomMailTemplate> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomMailTemplate: HttpResponse<EcomMailTemplate>) => {
          if (ecomMailTemplate.body) {
            return of(ecomMailTemplate.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomMailTemplate());
  }
}

export const ecomMailTemplateRoute: Routes = [
  {
    path: '',
    component: EcomMailTemplateComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMailTemplate.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EcomMailTemplateDetailComponent,
    resolve: {
      ecomMailTemplate: EcomMailTemplateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMailTemplate.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EcomMailTemplateUpdateComponent,
    resolve: {
      ecomMailTemplate: EcomMailTemplateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMailTemplate.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EcomMailTemplateUpdateComponent,
    resolve: {
      ecomMailTemplate: EcomMailTemplateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomMailTemplate.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
