import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IElasticSearchWoSalesAgent } from 'app/shared/model/elastic-search-wo-sales-agent.model';

type EntityResponseType = HttpResponse<IElasticSearchWoSalesAgent>;
type EntityArrayResponseType = HttpResponse<IElasticSearchWoSalesAgent[]>;

@Injectable({ providedIn: 'root' })
export class ElasticSearchWoSalesAgentService {
  public resourceUrl = SERVER_API_URL + 'api/elastic-search-wo-sales-agents';

  constructor(protected http: HttpClient) {}

  create(elasticSearchWoSalesAgent: IElasticSearchWoSalesAgent): Observable<EntityResponseType> {
    return this.http.post<IElasticSearchWoSalesAgent>(this.resourceUrl, elasticSearchWoSalesAgent, { observe: 'response' });
  }

  update(elasticSearchWoSalesAgent: IElasticSearchWoSalesAgent): Observable<EntityResponseType> {
    return this.http.put<IElasticSearchWoSalesAgent>(this.resourceUrl, elasticSearchWoSalesAgent, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IElasticSearchWoSalesAgent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IElasticSearchWoSalesAgent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
