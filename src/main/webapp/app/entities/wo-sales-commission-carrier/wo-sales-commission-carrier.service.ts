import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWoSalesCommissionCarrier } from 'app/shared/model/wo-sales-commission-carrier.model';

type EntityResponseType = HttpResponse<IWoSalesCommissionCarrier>;
type EntityArrayResponseType = HttpResponse<IWoSalesCommissionCarrier[]>;

@Injectable({ providedIn: 'root' })
export class WoSalesCommissionCarrierService {
  public resourceUrl = SERVER_API_URL + 'api/wo-sales-commission-carriers';

  constructor(protected http: HttpClient) {}

  create(woSalesCommissionCarrier: IWoSalesCommissionCarrier): Observable<EntityResponseType> {
    return this.http.post<IWoSalesCommissionCarrier>(this.resourceUrl, woSalesCommissionCarrier, { observe: 'response' });
  }

  update(woSalesCommissionCarrier: IWoSalesCommissionCarrier): Observable<EntityResponseType> {
    return this.http.put<IWoSalesCommissionCarrier>(this.resourceUrl, woSalesCommissionCarrier, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWoSalesCommissionCarrier>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWoSalesCommissionCarrier[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
