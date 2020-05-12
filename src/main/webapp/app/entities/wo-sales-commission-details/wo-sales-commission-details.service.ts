import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWoSalesCommissionDetails } from 'app/shared/model/wo-sales-commission-details.model';

type EntityResponseType = HttpResponse<IWoSalesCommissionDetails>;
type EntityArrayResponseType = HttpResponse<IWoSalesCommissionDetails[]>;

@Injectable({ providedIn: 'root' })
export class WoSalesCommissionDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/wo-sales-commission-details';

  constructor(protected http: HttpClient) {}

  create(woSalesCommissionDetails: IWoSalesCommissionDetails): Observable<EntityResponseType> {
    return this.http.post<IWoSalesCommissionDetails>(this.resourceUrl, woSalesCommissionDetails, { observe: 'response' });
  }

  update(woSalesCommissionDetails: IWoSalesCommissionDetails): Observable<EntityResponseType> {
    return this.http.put<IWoSalesCommissionDetails>(this.resourceUrl, woSalesCommissionDetails, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWoSalesCommissionDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWoSalesCommissionDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
