import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEcomStoreShipmentSettings, getEcomStoreShipmentSettingsIdentifier } from '../ecom-store-shipment-settings.model';

export type EntityResponseType = HttpResponse<IEcomStoreShipmentSettings>;
export type EntityArrayResponseType = HttpResponse<IEcomStoreShipmentSettings[]>;

@Injectable({ providedIn: 'root' })
export class EcomStoreShipmentSettingsService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/ecom-store-shipment-settings');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(ecomStoreShipmentSettings: IEcomStoreShipmentSettings): Observable<EntityResponseType> {
    return this.http.post<IEcomStoreShipmentSettings>(this.resourceUrl, ecomStoreShipmentSettings, { observe: 'response' });
  }

  update(ecomStoreShipmentSettings: IEcomStoreShipmentSettings): Observable<EntityResponseType> {
    return this.http.put<IEcomStoreShipmentSettings>(
      `${this.resourceUrl}/${getEcomStoreShipmentSettingsIdentifier(ecomStoreShipmentSettings) as number}`,
      ecomStoreShipmentSettings,
      { observe: 'response' }
    );
  }

  partialUpdate(ecomStoreShipmentSettings: IEcomStoreShipmentSettings): Observable<EntityResponseType> {
    return this.http.patch<IEcomStoreShipmentSettings>(
      `${this.resourceUrl}/${getEcomStoreShipmentSettingsIdentifier(ecomStoreShipmentSettings) as number}`,
      ecomStoreShipmentSettings,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomStoreShipmentSettings>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomStoreShipmentSettings[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addEcomStoreShipmentSettingsToCollectionIfMissing(
    ecomStoreShipmentSettingsCollection: IEcomStoreShipmentSettings[],
    ...ecomStoreShipmentSettingsToCheck: (IEcomStoreShipmentSettings | null | undefined)[]
  ): IEcomStoreShipmentSettings[] {
    const ecomStoreShipmentSettings: IEcomStoreShipmentSettings[] = ecomStoreShipmentSettingsToCheck.filter(isPresent);
    if (ecomStoreShipmentSettings.length > 0) {
      const ecomStoreShipmentSettingsCollectionIdentifiers = ecomStoreShipmentSettingsCollection.map(
        ecomStoreShipmentSettingsItem => getEcomStoreShipmentSettingsIdentifier(ecomStoreShipmentSettingsItem)!
      );
      const ecomStoreShipmentSettingsToAdd = ecomStoreShipmentSettings.filter(ecomStoreShipmentSettingsItem => {
        const ecomStoreShipmentSettingsIdentifier = getEcomStoreShipmentSettingsIdentifier(ecomStoreShipmentSettingsItem);
        if (
          ecomStoreShipmentSettingsIdentifier == null ||
          ecomStoreShipmentSettingsCollectionIdentifiers.includes(ecomStoreShipmentSettingsIdentifier)
        ) {
          return false;
        }
        ecomStoreShipmentSettingsCollectionIdentifiers.push(ecomStoreShipmentSettingsIdentifier);
        return true;
      });
      return [...ecomStoreShipmentSettingsToAdd, ...ecomStoreShipmentSettingsCollection];
    }
    return ecomStoreShipmentSettingsCollection;
  }
}
