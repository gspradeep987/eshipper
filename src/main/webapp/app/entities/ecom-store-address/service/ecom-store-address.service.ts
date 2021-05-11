import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEcomStoreAddress, getEcomStoreAddressIdentifier } from '../ecom-store-address.model';

export type EntityResponseType = HttpResponse<IEcomStoreAddress>;
export type EntityArrayResponseType = HttpResponse<IEcomStoreAddress[]>;

@Injectable({ providedIn: 'root' })
export class EcomStoreAddressService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/ecom-store-addresses');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(ecomStoreAddress: IEcomStoreAddress): Observable<EntityResponseType> {
    return this.http.post<IEcomStoreAddress>(this.resourceUrl, ecomStoreAddress, { observe: 'response' });
  }

  update(ecomStoreAddress: IEcomStoreAddress): Observable<EntityResponseType> {
    return this.http.put<IEcomStoreAddress>(
      `${this.resourceUrl}/${getEcomStoreAddressIdentifier(ecomStoreAddress) as number}`,
      ecomStoreAddress,
      { observe: 'response' }
    );
  }

  partialUpdate(ecomStoreAddress: IEcomStoreAddress): Observable<EntityResponseType> {
    return this.http.patch<IEcomStoreAddress>(
      `${this.resourceUrl}/${getEcomStoreAddressIdentifier(ecomStoreAddress) as number}`,
      ecomStoreAddress,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomStoreAddress>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomStoreAddress[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addEcomStoreAddressToCollectionIfMissing(
    ecomStoreAddressCollection: IEcomStoreAddress[],
    ...ecomStoreAddressesToCheck: (IEcomStoreAddress | null | undefined)[]
  ): IEcomStoreAddress[] {
    const ecomStoreAddresses: IEcomStoreAddress[] = ecomStoreAddressesToCheck.filter(isPresent);
    if (ecomStoreAddresses.length > 0) {
      const ecomStoreAddressCollectionIdentifiers = ecomStoreAddressCollection.map(
        ecomStoreAddressItem => getEcomStoreAddressIdentifier(ecomStoreAddressItem)!
      );
      const ecomStoreAddressesToAdd = ecomStoreAddresses.filter(ecomStoreAddressItem => {
        const ecomStoreAddressIdentifier = getEcomStoreAddressIdentifier(ecomStoreAddressItem);
        if (ecomStoreAddressIdentifier == null || ecomStoreAddressCollectionIdentifiers.includes(ecomStoreAddressIdentifier)) {
          return false;
        }
        ecomStoreAddressCollectionIdentifiers.push(ecomStoreAddressIdentifier);
        return true;
      });
      return [...ecomStoreAddressesToAdd, ...ecomStoreAddressCollection];
    }
    return ecomStoreAddressCollection;
  }
}
