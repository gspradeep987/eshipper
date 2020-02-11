import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomWarehouse } from 'app/shared/model/ecom-warehouse.model';

type EntityResponseType = HttpResponse<IEcomWarehouse>;
type EntityArrayResponseType = HttpResponse<IEcomWarehouse[]>;

@Injectable({ providedIn: 'root' })
export class EcomWarehouseService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-warehouses';

  constructor(protected http: HttpClient) {}

  create(ecomWarehouse: IEcomWarehouse): Observable<EntityResponseType> {
    return this.http.post<IEcomWarehouse>(this.resourceUrl, ecomWarehouse, { observe: 'response' });
  }

  update(ecomWarehouse: IEcomWarehouse): Observable<EntityResponseType> {
    return this.http.put<IEcomWarehouse>(this.resourceUrl, ecomWarehouse, { observe: 'response' });
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
}
