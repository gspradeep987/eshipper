import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICompanyCarrierAccount } from 'app/shared/model/company-carrier-account.model';

type EntityResponseType = HttpResponse<ICompanyCarrierAccount>;
type EntityArrayResponseType = HttpResponse<ICompanyCarrierAccount[]>;

@Injectable({ providedIn: 'root' })
export class CompanyCarrierAccountService {
  public resourceUrl = SERVER_API_URL + 'api/company-carrier-accounts';

  constructor(protected http: HttpClient) {}

  create(companyCarrierAccount: ICompanyCarrierAccount): Observable<EntityResponseType> {
    return this.http.post<ICompanyCarrierAccount>(this.resourceUrl, companyCarrierAccount, { observe: 'response' });
  }

  update(companyCarrierAccount: ICompanyCarrierAccount): Observable<EntityResponseType> {
    return this.http.put<ICompanyCarrierAccount>(this.resourceUrl, companyCarrierAccount, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICompanyCarrierAccount>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICompanyCarrierAccount[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
