import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEcomWarehouse, EcomWarehouse } from '../ecom-warehouse.model';
import { EcomWarehouseService } from '../service/ecom-warehouse.service';

@Injectable({ providedIn: 'root' })
export class EcomWarehouseRoutingResolveService implements Resolve<IEcomWarehouse> {
  constructor(protected service: EcomWarehouseService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomWarehouse> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ecomWarehouse: HttpResponse<EcomWarehouse>) => {
          if (ecomWarehouse.body) {
            return of(ecomWarehouse.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomWarehouse());
  }
}
