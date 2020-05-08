import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomOrderAttachment } from 'app/shared/model/ecom-order-attachment.model';

type EntityResponseType = HttpResponse<IEcomOrderAttachment>;
type EntityArrayResponseType = HttpResponse<IEcomOrderAttachment[]>;

@Injectable({ providedIn: 'root' })
export class EcomOrderAttachmentService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-order-attachments';

  constructor(protected http: HttpClient) {}

  create(ecomOrderAttachment: IEcomOrderAttachment): Observable<EntityResponseType> {
    return this.http.post<IEcomOrderAttachment>(this.resourceUrl, ecomOrderAttachment, { observe: 'response' });
  }

  update(ecomOrderAttachment: IEcomOrderAttachment): Observable<EntityResponseType> {
    return this.http.put<IEcomOrderAttachment>(this.resourceUrl, ecomOrderAttachment, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomOrderAttachment>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomOrderAttachment[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
