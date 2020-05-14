import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUser10 } from 'app/shared/model/user-10.model';

type EntityResponseType = HttpResponse<IUser10>;
type EntityArrayResponseType = HttpResponse<IUser10[]>;

@Injectable({ providedIn: 'root' })
export class User10Service {
  public resourceUrl = SERVER_API_URL + 'api/user-10-s';

  constructor(protected http: HttpClient) {}

  create(user10: IUser10): Observable<EntityResponseType> {
    return this.http.post<IUser10>(this.resourceUrl, user10, { observe: 'response' });
  }

  update(user10: IUser10): Observable<EntityResponseType> {
    return this.http.put<IUser10>(this.resourceUrl, user10, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUser10>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUser10[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
