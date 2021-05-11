import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEcomProductImage, getEcomProductImageIdentifier } from '../ecom-product-image.model';

export type EntityResponseType = HttpResponse<IEcomProductImage>;
export type EntityArrayResponseType = HttpResponse<IEcomProductImage[]>;

@Injectable({ providedIn: 'root' })
export class EcomProductImageService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/ecom-product-images');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(ecomProductImage: IEcomProductImage): Observable<EntityResponseType> {
    return this.http.post<IEcomProductImage>(this.resourceUrl, ecomProductImage, { observe: 'response' });
  }

  update(ecomProductImage: IEcomProductImage): Observable<EntityResponseType> {
    return this.http.put<IEcomProductImage>(
      `${this.resourceUrl}/${getEcomProductImageIdentifier(ecomProductImage) as number}`,
      ecomProductImage,
      { observe: 'response' }
    );
  }

  partialUpdate(ecomProductImage: IEcomProductImage): Observable<EntityResponseType> {
    return this.http.patch<IEcomProductImage>(
      `${this.resourceUrl}/${getEcomProductImageIdentifier(ecomProductImage) as number}`,
      ecomProductImage,
      { observe: 'response' }
    );
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

  addEcomProductImageToCollectionIfMissing(
    ecomProductImageCollection: IEcomProductImage[],
    ...ecomProductImagesToCheck: (IEcomProductImage | null | undefined)[]
  ): IEcomProductImage[] {
    const ecomProductImages: IEcomProductImage[] = ecomProductImagesToCheck.filter(isPresent);
    if (ecomProductImages.length > 0) {
      const ecomProductImageCollectionIdentifiers = ecomProductImageCollection.map(
        ecomProductImageItem => getEcomProductImageIdentifier(ecomProductImageItem)!
      );
      const ecomProductImagesToAdd = ecomProductImages.filter(ecomProductImageItem => {
        const ecomProductImageIdentifier = getEcomProductImageIdentifier(ecomProductImageItem);
        if (ecomProductImageIdentifier == null || ecomProductImageCollectionIdentifiers.includes(ecomProductImageIdentifier)) {
          return false;
        }
        ecomProductImageCollectionIdentifiers.push(ecomProductImageIdentifier);
        return true;
      });
      return [...ecomProductImagesToAdd, ...ecomProductImageCollection];
    }
    return ecomProductImageCollection;
  }
}
