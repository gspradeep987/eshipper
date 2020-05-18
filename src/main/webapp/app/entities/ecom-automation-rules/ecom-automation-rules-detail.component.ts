import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomAutomationRules } from 'app/shared/model/ecom-automation-rules.model';

@Component({
  selector: 'jhi-ecom-automation-rules-detail',
  templateUrl: './ecom-automation-rules-detail.component.html',
})
export class EcomAutomationRulesDetailComponent implements OnInit {
  ecomAutomationRules: IEcomAutomationRules | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomAutomationRules }) => (this.ecomAutomationRules = ecomAutomationRules));
  }

  previousState(): void {
    window.history.back();
  }
}
