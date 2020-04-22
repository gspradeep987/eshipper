import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISignatureRequired } from 'app/shared/model/signature-required.model';

type EntityResponseType = HttpResponse<ISignatureRequired>;
type EntityArrayResponseType = HttpResponse<ISignatureRequired[]>;

@Injectable({ providedIn: 'root' })
export class SignatureRequiredService {
  public resourceUrl = SERVER_API_URL + 'api/signature-requireds';

  constructor(protected http: HttpClient) {}

  create(signatureRequired: ISignatureRequired): Observable<EntityResponseType> {
    return this.http.post<ISignatureRequired>(this.resourceUrl, signatureRequired, { observe: 'response' });
  }

  update(signatureRequired: ISignatureRequired): Observable<EntityResponseType> {
    return this.http.put<ISignatureRequired>(this.resourceUrl, signatureRequired, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISignatureRequired>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISignatureRequired[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
