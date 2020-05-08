import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomOrder } from 'app/shared/model/ecom-order.model';

type EntityResponseType = HttpResponse<IEcomOrder>;
type EntityArrayResponseType = HttpResponse<IEcomOrder[]>;

@Injectable({ providedIn: 'root' })
export class EcomOrderService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-orders';

  constructor(protected http: HttpClient) {}

  create(ecomOrder: IEcomOrder): Observable<EntityResponseType> {
    return this.http.post<IEcomOrder>(this.resourceUrl, ecomOrder, { observe: 'response' });
  }

  update(ecomOrder: IEcomOrder): Observable<EntityResponseType> {
    return this.http.put<IEcomOrder>(this.resourceUrl, ecomOrder, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomOrder>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomOrder[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
