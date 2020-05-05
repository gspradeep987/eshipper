import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICompanyEcomSettings } from 'app/shared/model/company-ecom-settings.model';

@Component({
  selector: 'jhi-company-ecom-settings-detail',
  templateUrl: './company-ecom-settings-detail.component.html'
})
export class CompanyEcomSettingsDetailComponent implements OnInit {
  companyEcomSettings: ICompanyEcomSettings | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ companyEcomSettings }) => (this.companyEcomSettings = companyEcomSettings));
  }

  previousState(): void {
    window.history.back();
  }
}
