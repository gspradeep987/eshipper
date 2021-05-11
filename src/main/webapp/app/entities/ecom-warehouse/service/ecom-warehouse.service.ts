import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEcomWarehouse, getEcomWarehouseIdentifier } from '../ecom-warehouse.model';

export type EntityResponseType = HttpResponse<IEcomWarehouse>;
export type EntityArrayResponseType = HttpResponse<IEcomWarehouse[]>;

@Injectable({ providedIn: 'root' })
export class EcomWarehouseService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/ecom-warehouses');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(ecomWarehouse: IEcomWarehouse): Observable<EntityResponseType> {
    return this.http.post<IEcomWarehouse>(this.resourceUrl, ecomWarehouse, { observe: 'response' });
  }

  update(ecomWarehouse: IEcomWarehouse): Observable<EntityResponseType> {
    return this.http.put<IEcomWarehouse>(`${this.resourceUrl}/${getEcomWarehouseIdentifier(ecomWarehouse) as number}`, ecomWarehouse, {
      observe: 'response',
    });
  }

  partialUpdate(ecomWarehouse: IEcomWarehouse): Observable<EntityResponseType> {
    return this.http.patch<IEcomWarehouse>(`${this.resourceUrl}/${getEcomWarehouseIdentifier(ecomWarehouse) as number}`, ecomWarehouse, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomWarehouse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomWarehouse[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addEcomWarehouseToCollectionIfMissing(
    ecomWarehouseCollection: IEcomWarehouse[],
    ...ecomWarehousesToCheck: (IEcomWarehouse | null | undefined)[]
  ): IEcomWarehouse[] {
    const ecomWarehouses: IEcomWarehouse[] = ecomWarehousesToCheck.filter(isPresent);
    if (ecomWarehouses.length > 0) {
      const ecomWarehouseCollectionIdentifiers = ecomWarehouseCollection.map(
        ecomWarehouseItem => getEcomWarehouseIdentifier(ecomWarehouseItem)!
      );
      const ecomWarehousesToAdd = ecomWarehouses.filter(ecomWarehouseItem => {
        const ecomWarehouseIdentifier = getEcomWarehouseIdentifier(ecomWarehouseItem);
        if (ecomWarehouseIdentifier == null || ecomWarehouseCollectionIdentifiers.includes(ecomWarehouseIdentifier)) {
          return false;
        }
        ecomWarehouseCollectionIdentifiers.push(ecomWarehouseIdentifier);
        return true;
      });
      return [...ecomWarehousesToAdd, ...ecomWarehouseCollection];
    }
    return ecomWarehouseCollection;
  }
}
