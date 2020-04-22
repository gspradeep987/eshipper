import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICompanyEcomSettings, CompanyEcomSettings } from 'app/shared/model/company-ecom-settings.model';
import { CompanyEcomSettingsService } from './company-ecom-settings.service';
import { CompanyEcomSettingsComponent } from './company-ecom-settings.component';
import { CompanyEcomSettingsDetailComponent } from './company-ecom-settings-detail.component';
import { CompanyEcomSettingsUpdateComponent } from './company-ecom-settings-update.component';

@Injectable({ providedIn: 'root' })
export class CompanyEcomSettingsResolve implements Resolve<ICompanyEcomSettings> {
  constructor(private service: CompanyEcomSettingsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICompanyEcomSettings> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((companyEcomSettings: HttpResponse<CompanyEcomSettings>) => {
          if (companyEcomSettings.body) {
            return of(companyEcomSettings.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CompanyEcomSettings());
  }
}

export const companyEcomSettingsRoute: Routes = [
  {
    path: '',
    component: CompanyEcomSettingsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.companyEcomSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CompanyEcomSettingsDetailComponent,
    resolve: {
      companyEcomSettings: CompanyEcomSettingsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.companyEcomSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CompanyEcomSettingsUpdateComponent,
    resolve: {
      companyEcomSettings: CompanyEcomSettingsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.companyEcomSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CompanyEcomSettingsUpdateComponent,
    resolve: {
      companyEcomSettings: CompanyEcomSettingsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.companyEcomSettings.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
