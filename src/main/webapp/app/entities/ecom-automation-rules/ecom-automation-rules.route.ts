import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomAutomationRules, EcomAutomationRules } from 'app/shared/model/ecom-automation-rules.model';
import { EcomAutomationRulesService } from './ecom-automation-rules.service';
import { EcomAutomationRulesComponent } from './ecom-automation-rules.component';
import { EcomAutomationRulesDetailComponent } from './ecom-automation-rules-detail.component';
import { EcomAutomationRulesUpdateComponent } from './ecom-automation-rules-update.component';

@Injectable({ providedIn: 'root' })
export class EcomAutomationRulesResolve implements Resolve<IEcomAutomationRules> {
  constructor(private service: EcomAutomationRulesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomAutomationRules> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomAutomationRules: HttpResponse<EcomAutomationRules>) => {
          if (ecomAutomationRules.body) {
            return of(ecomAutomationRules.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomAutomationRules());
  }
}

export const ecomAutomationRulesRoute: Routes = [
  {
    path: '',
    component: EcomAutomationRulesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomAutomationRules.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomAutomationRulesDetailComponent,
    resolve: {
      ecomAutomationRules: EcomAutomationRulesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomAutomationRules.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomAutomationRulesUpdateComponent,
    resolve: {
      ecomAutomationRules: EcomAutomationRulesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomAutomationRules.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomAutomationRulesUpdateComponent,
    resolve: {
      ecomAutomationRules: EcomAutomationRulesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomAutomationRules.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
