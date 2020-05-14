import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWoSalesOperationalCarrier } from 'app/shared/model/wo-sales-operational-carrier.model';

type EntityResponseType = HttpResponse<IWoSalesOperationalCarrier>;
type EntityArrayResponseType = HttpResponse<IWoSalesOperationalCarrier[]>;

@Injectable({ providedIn: 'root' })
export class WoSalesOperationalCarrierService {
  public resourceUrl = SERVER_API_URL + 'api/wo-sales-operational-carriers';

  constructor(protected http: HttpClient) {}

  create(woSalesOperationalCarrier: IWoSalesOperationalCarrier): Observable<EntityResponseType> {
    return this.http.post<IWoSalesOperationalCarrier>(this.resourceUrl, woSalesOperationalCarrier, { observe: 'response' });
  }

  update(woSalesOperationalCarrier: IWoSalesOperationalCarrier): Observable<EntityResponseType> {
    return this.http.put<IWoSalesOperationalCarrier>(this.resourceUrl, woSalesOperationalCarrier, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWoSalesOperationalCarrier>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWoSalesOperationalCarrier[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
