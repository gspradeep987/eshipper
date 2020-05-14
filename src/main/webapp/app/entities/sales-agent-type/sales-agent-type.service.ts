import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISalesAgentType } from 'app/shared/model/sales-agent-type.model';

type EntityResponseType = HttpResponse<ISalesAgentType>;
type EntityArrayResponseType = HttpResponse<ISalesAgentType[]>;

@Injectable({ providedIn: 'root' })
export class SalesAgentTypeService {
  public resourceUrl = SERVER_API_URL + 'api/sales-agent-types';

  constructor(protected http: HttpClient) {}

  create(salesAgentType: ISalesAgentType): Observable<EntityResponseType> {
    return this.http.post<ISalesAgentType>(this.resourceUrl, salesAgentType, { observe: 'response' });
  }

  update(salesAgentType: ISalesAgentType): Observable<EntityResponseType> {
    return this.http.put<ISalesAgentType>(this.resourceUrl, salesAgentType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISalesAgentType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISalesAgentType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
