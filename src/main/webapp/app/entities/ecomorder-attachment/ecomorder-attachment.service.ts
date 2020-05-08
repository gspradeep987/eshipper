import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomorderAttachment } from 'app/shared/model/ecomorder-attachment.model';

type EntityResponseType = HttpResponse<IEcomorderAttachment>;
type EntityArrayResponseType = HttpResponse<IEcomorderAttachment[]>;

@Injectable({ providedIn: 'root' })
export class EcomorderAttachmentService {
  public resourceUrl = SERVER_API_URL + 'api/ecomorder-attachments';

  constructor(protected http: HttpClient) {}

  create(ecomorderAttachment: IEcomorderAttachment): Observable<EntityResponseType> {
    return this.http.post<IEcomorderAttachment>(this.resourceUrl, ecomorderAttachment, { observe: 'response' });
  }

  update(ecomorderAttachment: IEcomorderAttachment): Observable<EntityResponseType> {
    return this.http.put<IEcomorderAttachment>(this.resourceUrl, ecomorderAttachment, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomorderAttachment>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomorderAttachment[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
