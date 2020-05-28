import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWoSalesOperationalDetails } from 'app/shared/model/wo-sales-operational-details.model';

type EntityResponseType = HttpResponse<IWoSalesOperationalDetails>;
type EntityArrayResponseType = HttpResponse<IWoSalesOperationalDetails[]>;

@Injectable({ providedIn: 'root' })
export class WoSalesOperationalDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/wo-sales-operational-details';

  constructor(protected http: HttpClient) {}

  create(woSalesOperationalDetails: IWoSalesOperationalDetails): Observable<EntityResponseType> {
    return this.http.post<IWoSalesOperationalDetails>(this.resourceUrl, woSalesOperationalDetails, { observe: 'response' });
  }

  update(woSalesOperationalDetails: IWoSalesOperationalDetails): Observable<EntityResponseType> {
    return this.http.put<IWoSalesOperationalDetails>(this.resourceUrl, woSalesOperationalDetails, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWoSalesOperationalDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWoSalesOperationalDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
