import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISisyphusClient, getSisyphusClientIdentifier } from '../sisyphus-client.model';

export type EntityResponseType = HttpResponse<ISisyphusClient>;
export type EntityArrayResponseType = HttpResponse<ISisyphusClient[]>;

@Injectable({ providedIn: 'root' })
export class SisyphusClientService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/sisyphus-clients');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(sisyphusClient: ISisyphusClient): Observable<EntityResponseType> {
    return this.http.post<ISisyphusClient>(this.resourceUrl, sisyphusClient, { observe: 'response' });
  }

  update(sisyphusClient: ISisyphusClient): Observable<EntityResponseType> {
    return this.http.put<ISisyphusClient>(`${this.resourceUrl}/${getSisyphusClientIdentifier(sisyphusClient) as number}`, sisyphusClient, {
      observe: 'response',
    });
  }

  partialUpdate(sisyphusClient: ISisyphusClient): Observable<EntityResponseType> {
    return this.http.patch<ISisyphusClient>(
      `${this.resourceUrl}/${getSisyphusClientIdentifier(sisyphusClient) as number}`,
      sisyphusClient,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISisyphusClient>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISisyphusClient[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addSisyphusClientToCollectionIfMissing(
    sisyphusClientCollection: ISisyphusClient[],
    ...sisyphusClientsToCheck: (ISisyphusClient | null | undefined)[]
  ): ISisyphusClient[] {
    const sisyphusClients: ISisyphusClient[] = sisyphusClientsToCheck.filter(isPresent);
    if (sisyphusClients.length > 0) {
      const sisyphusClientCollectionIdentifiers = sisyphusClientCollection.map(
        sisyphusClientItem => getSisyphusClientIdentifier(sisyphusClientItem)!
      );
      const sisyphusClientsToAdd = sisyphusClients.filter(sisyphusClientItem => {
        const sisyphusClientIdentifier = getSisyphusClientIdentifier(sisyphusClientItem);
        if (sisyphusClientIdentifier == null || sisyphusClientCollectionIdentifiers.includes(sisyphusClientIdentifier)) {
          return false;
        }
        sisyphusClientCollectionIdentifiers.push(sisyphusClientIdentifier);
        return true;
      });
      return [...sisyphusClientsToAdd, ...sisyphusClientCollection];
    }
    return sisyphusClientCollection;
  }
}
