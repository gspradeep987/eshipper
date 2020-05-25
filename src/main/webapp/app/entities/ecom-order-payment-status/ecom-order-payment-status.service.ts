import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomOrderPaymentStatus } from 'app/shared/model/ecom-order-payment-status.model';

type EntityResponseType = HttpResponse<IEcomOrderPaymentStatus>;
type EntityArrayResponseType = HttpResponse<IEcomOrderPaymentStatus[]>;

@Injectable({ providedIn: 'root' })
export class EcomOrderPaymentStatusService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-order-payment-statuses';

  constructor(protected http: HttpClient) {}

  create(ecomOrderPaymentStatus: IEcomOrderPaymentStatus): Observable<EntityResponseType> {
    return this.http.post<IEcomOrderPaymentStatus>(this.resourceUrl, ecomOrderPaymentStatus, { observe: 'response' });
  }

  update(ecomOrderPaymentStatus: IEcomOrderPaymentStatus): Observable<EntityResponseType> {
    return this.http.put<IEcomOrderPaymentStatus>(this.resourceUrl, ecomOrderPaymentStatus, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomOrderPaymentStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomOrderPaymentStatus[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
