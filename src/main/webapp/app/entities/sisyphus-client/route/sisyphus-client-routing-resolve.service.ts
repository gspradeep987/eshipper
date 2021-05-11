import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISisyphusClient, SisyphusClient } from '../sisyphus-client.model';
import { SisyphusClientService } from '../service/sisyphus-client.service';

@Injectable({ providedIn: 'root' })
export class SisyphusClientRoutingResolveService implements Resolve<ISisyphusClient> {
  constructor(protected service: SisyphusClientService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISisyphusClient> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((sisyphusClient: HttpResponse<SisyphusClient>) => {
          if (sisyphusClient.body) {
            return of(sisyphusClient.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SisyphusClient());
  }
}
