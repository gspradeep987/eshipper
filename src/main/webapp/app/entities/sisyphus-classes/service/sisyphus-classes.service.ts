import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISisyphusClasses, getSisyphusClassesIdentifier } from '../sisyphus-classes.model';

export type EntityResponseType = HttpResponse<ISisyphusClasses>;
export type EntityArrayResponseType = HttpResponse<ISisyphusClasses[]>;

@Injectable({ providedIn: 'root' })
export class SisyphusClassesService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/sisyphus-classes');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(sisyphusClasses: ISisyphusClasses): Observable<EntityResponseType> {
    return this.http.post<ISisyphusClasses>(this.resourceUrl, sisyphusClasses, { observe: 'response' });
  }

  update(sisyphusClasses: ISisyphusClasses): Observable<EntityResponseType> {
    return this.http.put<ISisyphusClasses>(
      `${this.resourceUrl}/${getSisyphusClassesIdentifier(sisyphusClasses) as number}`,
      sisyphusClasses,
      { observe: 'response' }
    );
  }

  partialUpdate(sisyphusClasses: ISisyphusClasses): Observable<EntityResponseType> {
    return this.http.patch<ISisyphusClasses>(
      `${this.resourceUrl}/${getSisyphusClassesIdentifier(sisyphusClasses) as number}`,
      sisyphusClasses,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISisyphusClasses>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISisyphusClasses[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addSisyphusClassesToCollectionIfMissing(
    sisyphusClassesCollection: ISisyphusClasses[],
    ...sisyphusClassesToCheck: (ISisyphusClasses | null | undefined)[]
  ): ISisyphusClasses[] {
    const sisyphusClasses: ISisyphusClasses[] = sisyphusClassesToCheck.filter(isPresent);
    if (sisyphusClasses.length > 0) {
      const sisyphusClassesCollectionIdentifiers = sisyphusClassesCollection.map(
        sisyphusClassesItem => getSisyphusClassesIdentifier(sisyphusClassesItem)!
      );
      const sisyphusClassesToAdd = sisyphusClasses.filter(sisyphusClassesItem => {
        const sisyphusClassesIdentifier = getSisyphusClassesIdentifier(sisyphusClassesItem);
        if (sisyphusClassesIdentifier == null || sisyphusClassesCollectionIdentifiers.includes(sisyphusClassesIdentifier)) {
          return false;
        }
        sisyphusClassesCollectionIdentifiers.push(sisyphusClassesIdentifier);
        return true;
      });
      return [...sisyphusClassesToAdd, ...sisyphusClassesCollection];
    }
    return sisyphusClassesCollection;
  }
}
