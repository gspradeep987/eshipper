import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWoSalesAgent } from 'app/shared/model/wo-sales-agent.model';

type EntityResponseType = HttpResponse<IWoSalesAgent>;
type EntityArrayResponseType = HttpResponse<IWoSalesAgent[]>;

@Injectable({ providedIn: 'root' })
export class WoSalesAgentService {
  public resourceUrl = SERVER_API_URL + 'api/wo-sales-agents';

  constructor(protected http: HttpClient) {}

  create(woSalesAgent: IWoSalesAgent): Observable<EntityResponseType> {
    return this.http.post<IWoSalesAgent>(this.resourceUrl, woSalesAgent, { observe: 'response' });
  }

  update(woSalesAgent: IWoSalesAgent): Observable<EntityResponseType> {
    return this.http.put<IWoSalesAgent>(this.resourceUrl, woSalesAgent, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWoSalesAgent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWoSalesAgent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
