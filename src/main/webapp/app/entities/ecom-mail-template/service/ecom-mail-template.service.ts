import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEcomMailTemplate, getEcomMailTemplateIdentifier } from '../ecom-mail-template.model';

export type EntityResponseType = HttpResponse<IEcomMailTemplate>;
export type EntityArrayResponseType = HttpResponse<IEcomMailTemplate[]>;

@Injectable({ providedIn: 'root' })
export class EcomMailTemplateService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/ecom-mail-templates');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(ecomMailTemplate: IEcomMailTemplate): Observable<EntityResponseType> {
    return this.http.post<IEcomMailTemplate>(this.resourceUrl, ecomMailTemplate, { observe: 'response' });
  }

  update(ecomMailTemplate: IEcomMailTemplate): Observable<EntityResponseType> {
    return this.http.put<IEcomMailTemplate>(
      `${this.resourceUrl}/${getEcomMailTemplateIdentifier(ecomMailTemplate) as number}`,
      ecomMailTemplate,
      { observe: 'response' }
    );
  }

  partialUpdate(ecomMailTemplate: IEcomMailTemplate): Observable<EntityResponseType> {
    return this.http.patch<IEcomMailTemplate>(
      `${this.resourceUrl}/${getEcomMailTemplateIdentifier(ecomMailTemplate) as number}`,
      ecomMailTemplate,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomMailTemplate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomMailTemplate[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addEcomMailTemplateToCollectionIfMissing(
    ecomMailTemplateCollection: IEcomMailTemplate[],
    ...ecomMailTemplatesToCheck: (IEcomMailTemplate | null | undefined)[]
  ): IEcomMailTemplate[] {
    const ecomMailTemplates: IEcomMailTemplate[] = ecomMailTemplatesToCheck.filter(isPresent);
    if (ecomMailTemplates.length > 0) {
      const ecomMailTemplateCollectionIdentifiers = ecomMailTemplateCollection.map(
        ecomMailTemplateItem => getEcomMailTemplateIdentifier(ecomMailTemplateItem)!
      );
      const ecomMailTemplatesToAdd = ecomMailTemplates.filter(ecomMailTemplateItem => {
        const ecomMailTemplateIdentifier = getEcomMailTemplateIdentifier(ecomMailTemplateItem);
        if (ecomMailTemplateIdentifier == null || ecomMailTemplateCollectionIdentifiers.includes(ecomMailTemplateIdentifier)) {
          return false;
        }
        ecomMailTemplateCollectionIdentifiers.push(ecomMailTemplateIdentifier);
        return true;
      });
      return [...ecomMailTemplatesToAdd, ...ecomMailTemplateCollection];
    }
    return ecomMailTemplateCollection;
  }
}
