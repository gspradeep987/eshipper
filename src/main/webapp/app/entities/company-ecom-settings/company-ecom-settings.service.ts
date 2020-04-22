import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICompanyEcomSettings } from 'app/shared/model/company-ecom-settings.model';

type EntityResponseType = HttpResponse<ICompanyEcomSettings>;
type EntityArrayResponseType = HttpResponse<ICompanyEcomSettings[]>;

@Injectable({ providedIn: 'root' })
export class CompanyEcomSettingsService {
  public resourceUrl = SERVER_API_URL + 'api/company-ecom-settings';

  constructor(protected http: HttpClient) {}

  create(companyEcomSettings: ICompanyEcomSettings): Observable<EntityResponseType> {
    return this.http.post<ICompanyEcomSettings>(this.resourceUrl, companyEcomSettings, { observe: 'response' });
  }

  update(companyEcomSettings: ICompanyEcomSettings): Observable<EntityResponseType> {
    return this.http.put<ICompanyEcomSettings>(this.resourceUrl, companyEcomSettings, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICompanyEcomSettings>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICompanyEcomSettings[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
