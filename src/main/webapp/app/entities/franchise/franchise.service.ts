import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFranchise } from 'app/shared/model/franchise.model';

type EntityResponseType = HttpResponse<IFranchise>;
type EntityArrayResponseType = HttpResponse<IFranchise[]>;

@Injectable({ providedIn: 'root' })
export class FranchiseService {
  public resourceUrl = SERVER_API_URL + 'api/franchises';

  constructor(protected http: HttpClient) {}

  create(franchise: IFranchise): Observable<EntityResponseType> {
    return this.http.post<IFranchise>(this.resourceUrl, franchise, { observe: 'response' });
  }

  update(franchise: IFranchise): Observable<EntityResponseType> {
    return this.http.put<IFranchise>(this.resourceUrl, franchise, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFranchise>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFranchise[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
