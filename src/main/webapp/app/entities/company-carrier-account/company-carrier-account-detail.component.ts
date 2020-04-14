import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICompanyCarrierAccount } from 'app/shared/model/company-carrier-account.model';

@Component({
  selector: 'jhi-company-carrier-account-detail',
  templateUrl: './company-carrier-account-detail.component.html'
})
export class CompanyCarrierAccountDetailComponent implements OnInit {
  companyCarrierAccount: ICompanyCarrierAccount | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ companyCarrierAccount }) => (this.companyCarrierAccount = companyCarrierAccount));
  }

  previousState(): void {
    window.history.back();
  }
}
