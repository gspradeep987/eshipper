import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IShippingAddress, getShippingAddressIdentifier } from '../shipping-address.model';

export type EntityResponseType = HttpResponse<IShippingAddress>;
export type EntityArrayResponseType = HttpResponse<IShippingAddress[]>;

@Injectable({ providedIn: 'root' })
export class ShippingAddressService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/shipping-addresses');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(shippingAddress: IShippingAddress): Observable<EntityResponseType> {
    return this.http.post<IShippingAddress>(this.resourceUrl, shippingAddress, { observe: 'response' });
  }

  update(shippingAddress: IShippingAddress): Observable<EntityResponseType> {
    return this.http.put<IShippingAddress>(
      `${this.resourceUrl}/${getShippingAddressIdentifier(shippingAddress) as number}`,
      shippingAddress,
      { observe: 'response' }
    );
  }

  partialUpdate(shippingAddress: IShippingAddress): Observable<EntityResponseType> {
    return this.http.patch<IShippingAddress>(
      `${this.resourceUrl}/${getShippingAddressIdentifier(shippingAddress) as number}`,
      shippingAddress,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IShippingAddress>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IShippingAddress[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addShippingAddressToCollectionIfMissing(
    shippingAddressCollection: IShippingAddress[],
    ...shippingAddressesToCheck: (IShippingAddress | null | undefined)[]
  ): IShippingAddress[] {
    const shippingAddresses: IShippingAddress[] = shippingAddressesToCheck.filter(isPresent);
    if (shippingAddresses.length > 0) {
      const shippingAddressCollectionIdentifiers = shippingAddressCollection.map(
        shippingAddressItem => getShippingAddressIdentifier(shippingAddressItem)!
      );
      const shippingAddressesToAdd = shippingAddresses.filter(shippingAddressItem => {
        const shippingAddressIdentifier = getShippingAddressIdentifier(shippingAddressItem);
        if (shippingAddressIdentifier == null || shippingAddressCollectionIdentifiers.includes(shippingAddressIdentifier)) {
          return false;
        }
        shippingAddressCollectionIdentifiers.push(shippingAddressIdentifier);
        return true;
      });
      return [...shippingAddressesToAdd, ...shippingAddressCollection];
    }
    return shippingAddressCollection;
  }
}
