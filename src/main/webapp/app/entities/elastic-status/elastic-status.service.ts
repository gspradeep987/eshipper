import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IElasticStatus } from 'app/shared/model/elastic-status.model';

type EntityResponseType = HttpResponse<IElasticStatus>;
type EntityArrayResponseType = HttpResponse<IElasticStatus[]>;

@Injectable({ providedIn: 'root' })
export class ElasticStatusService {
  public resourceUrl = SERVER_API_URL + 'api/elastic-statuses';

  constructor(protected http: HttpClient) {}

  create(elasticStatus: IElasticStatus): Observable<EntityResponseType> {
    return this.http.post<IElasticStatus>(this.resourceUrl, elasticStatus, { observe: 'response' });
  }

  update(elasticStatus: IElasticStatus): Observable<EntityResponseType> {
    return this.http.put<IElasticStatus>(this.resourceUrl, elasticStatus, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IElasticStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IElasticStatus[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
