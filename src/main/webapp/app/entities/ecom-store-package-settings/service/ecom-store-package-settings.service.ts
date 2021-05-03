import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEcomStorePackageSettings, getEcomStorePackageSettingsIdentifier } from '../ecom-store-package-settings.model';

export type EntityResponseType = HttpResponse<IEcomStorePackageSettings>;
export type EntityArrayResponseType = HttpResponse<IEcomStorePackageSettings[]>;

@Injectable({ providedIn: 'root' })
export class EcomStorePackageSettingsService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/ecom-store-package-settings');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(ecomStorePackageSettings: IEcomStorePackageSettings): Observable<EntityResponseType> {
    return this.http.post<IEcomStorePackageSettings>(this.resourceUrl, ecomStorePackageSettings, { observe: 'response' });
  }

  update(ecomStorePackageSettings: IEcomStorePackageSettings): Observable<EntityResponseType> {
    return this.http.put<IEcomStorePackageSettings>(
      `${this.resourceUrl}/${getEcomStorePackageSettingsIdentifier(ecomStorePackageSettings) as number}`,
      ecomStorePackageSettings,
      { observe: 'response' }
    );
  }

  partialUpdate(ecomStorePackageSettings: IEcomStorePackageSettings): Observable<EntityResponseType> {
    return this.http.patch<IEcomStorePackageSettings>(
      `${this.resourceUrl}/${getEcomStorePackageSettingsIdentifier(ecomStorePackageSettings) as number}`,
      ecomStorePackageSettings,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomStorePackageSettings>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomStorePackageSettings[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addEcomStorePackageSettingsToCollectionIfMissing(
    ecomStorePackageSettingsCollection: IEcomStorePackageSettings[],
    ...ecomStorePackageSettingsToCheck: (IEcomStorePackageSettings | null | undefined)[]
  ): IEcomStorePackageSettings[] {
    const ecomStorePackageSettings: IEcomStorePackageSettings[] = ecomStorePackageSettingsToCheck.filter(isPresent);
    if (ecomStorePackageSettings.length > 0) {
      const ecomStorePackageSettingsCollectionIdentifiers = ecomStorePackageSettingsCollection.map(
        ecomStorePackageSettingsItem => getEcomStorePackageSettingsIdentifier(ecomStorePackageSettingsItem)!
      );
      const ecomStorePackageSettingsToAdd = ecomStorePackageSettings.filter(ecomStorePackageSettingsItem => {
        const ecomStorePackageSettingsIdentifier = getEcomStorePackageSettingsIdentifier(ecomStorePackageSettingsItem);
        if (
          ecomStorePackageSettingsIdentifier == null ||
          ecomStorePackageSettingsCollectionIdentifiers.includes(ecomStorePackageSettingsIdentifier)
        ) {
          return false;
        }
        ecomStorePackageSettingsCollectionIdentifiers.push(ecomStorePackageSettingsIdentifier);
        return true;
      });
      return [...ecomStorePackageSettingsToAdd, ...ecomStorePackageSettingsCollection];
    }
    return ecomStorePackageSettingsCollection;
  }
}
