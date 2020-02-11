import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomProductImage } from 'app/shared/model/ecom-product-image.model';

type EntityResponseType = HttpResponse<IEcomProductImage>;
type EntityArrayResponseType = HttpResponse<IEcomProductImage[]>;

@Injectable({ providedIn: 'root' })
export class EcomProductImageService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-product-images';

  constructor(protected http: HttpClient) {}

  create(ecomProductImage: IEcomProductImage): Observable<EntityResponseType> {
    return this.http.post<IEcomProductImage>(this.resourceUrl, ecomProductImage, { observe: 'response' });
  }

  update(ecomProductImage: IEcomProductImage): Observable<EntityResponseType> {
    return this.http.put<IEcomProductImage>(this.resourceUrl, ecomProductImage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomProductImage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomProductImage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
