import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWoSalesAgentDetails } from 'app/shared/model/wo-sales-agent-details.model';

type EntityResponseType = HttpResponse<IWoSalesAgentDetails>;
type EntityArrayResponseType = HttpResponse<IWoSalesAgentDetails[]>;

@Injectable({ providedIn: 'root' })
export class WoSalesAgentDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/wo-sales-agent-details';

  constructor(protected http: HttpClient) {}

  create(woSalesAgentDetails: IWoSalesAgentDetails): Observable<EntityResponseType> {
    return this.http.post<IWoSalesAgentDetails>(this.resourceUrl, woSalesAgentDetails, { observe: 'response' });
  }

  update(woSalesAgentDetails: IWoSalesAgentDetails): Observable<EntityResponseType> {
    return this.http.put<IWoSalesAgentDetails>(this.resourceUrl, woSalesAgentDetails, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWoSalesAgentDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWoSalesAgentDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
