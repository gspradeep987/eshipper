import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomOrderFullfillmentStatus } from 'app/shared/model/ecom-order-fullfillment-status.model';

type EntityResponseType = HttpResponse<IEcomOrderFullfillmentStatus>;
type EntityArrayResponseType = HttpResponse<IEcomOrderFullfillmentStatus[]>;

@Injectable({ providedIn: 'root' })
export class EcomOrderFullfillmentStatusService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-order-fullfillment-statuses';

  constructor(protected http: HttpClient) {}

  create(ecomOrderFullfillmentStatus: IEcomOrderFullfillmentStatus): Observable<EntityResponseType> {
    return this.http.post<IEcomOrderFullfillmentStatus>(this.resourceUrl, ecomOrderFullfillmentStatus, { observe: 'response' });
  }

  update(ecomOrderFullfillmentStatus: IEcomOrderFullfillmentStatus): Observable<EntityResponseType> {
    return this.http.put<IEcomOrderFullfillmentStatus>(this.resourceUrl, ecomOrderFullfillmentStatus, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomOrderFullfillmentStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomOrderFullfillmentStatus[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
