import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomMailTemplate } from 'app/shared/model/ecom-mail-template.model';

type EntityResponseType = HttpResponse<IEcomMailTemplate>;
type EntityArrayResponseType = HttpResponse<IEcomMailTemplate[]>;

@Injectable({ providedIn: 'root' })
export class EcomMailTemplateService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-mail-templates';

  constructor(protected http: HttpClient) {}

  create(ecomMailTemplate: IEcomMailTemplate): Observable<EntityResponseType> {
    return this.http.post<IEcomMailTemplate>(this.resourceUrl, ecomMailTemplate, { observe: 'response' });
  }

  update(ecomMailTemplate: IEcomMailTemplate): Observable<EntityResponseType> {
    return this.http.put<IEcomMailTemplate>(this.resourceUrl, ecomMailTemplate, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomMailTemplate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomMailTemplate[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
