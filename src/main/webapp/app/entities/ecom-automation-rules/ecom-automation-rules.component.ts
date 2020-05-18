import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomAutomationRules } from 'app/shared/model/ecom-automation-rules.model';
import { EcomAutomationRulesService } from './ecom-automation-rules.service';
import { EcomAutomationRulesDeleteDialogComponent } from './ecom-automation-rules-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-automation-rules',
  templateUrl: './ecom-automation-rules.component.html',
})
export class EcomAutomationRulesComponent implements OnInit, OnDestroy {
  ecomAutomationRules?: IEcomAutomationRules[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomAutomationRulesService: EcomAutomationRulesService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomAutomationRulesService
      .query()
      .subscribe((res: HttpResponse<IEcomAutomationRules[]>) => (this.ecomAutomationRules = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomAutomationRules();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomAutomationRules): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomAutomationRules(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomAutomationRulesListModification', () => this.loadAll());
  }

  delete(ecomAutomationRules: IEcomAutomationRules): void {
    const modalRef = this.modalService.open(EcomAutomationRulesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomAutomationRules = ecomAutomationRules;
  }
}
