import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomStoreImages } from 'app/shared/model/ecom-store-images.model';

type EntityResponseType = HttpResponse<IEcomStoreImages>;
type EntityArrayResponseType = HttpResponse<IEcomStoreImages[]>;

@Injectable({ providedIn: 'root' })
export class EcomStoreImagesService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-store-images';

  constructor(protected http: HttpClient) {}

  create(ecomStoreImages: IEcomStoreImages): Observable<EntityResponseType> {
    return this.http.post<IEcomStoreImages>(this.resourceUrl, ecomStoreImages, { observe: 'response' });
  }

  update(ecomStoreImages: IEcomStoreImages): Observable<EntityResponseType> {
    return this.http.put<IEcomStoreImages>(this.resourceUrl, ecomStoreImages, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomStoreImages>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomStoreImages[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
