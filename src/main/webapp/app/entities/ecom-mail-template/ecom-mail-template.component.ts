import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomMailTemplate } from 'app/shared/model/ecom-mail-template.model';
import { EcomMailTemplateService } from './ecom-mail-template.service';
import { EcomMailTemplateDeleteDialogComponent } from './ecom-mail-template-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-mail-template',
  templateUrl: './ecom-mail-template.component.html'
})
export class EcomMailTemplateComponent implements OnInit, OnDestroy {
  ecomMailTemplates?: IEcomMailTemplate[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomMailTemplateService: EcomMailTemplateService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomMailTemplateService.query().subscribe((res: HttpResponse<IEcomMailTemplate[]>) => (this.ecomMailTemplates = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomMailTemplates();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomMailTemplate): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomMailTemplates(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomMailTemplateListModification', () => this.loadAll());
  }

  delete(ecomMailTemplate: IEcomMailTemplate): void {
    const modalRef = this.modalService.open(EcomMailTemplateDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomMailTemplate = ecomMailTemplate;
  }
}
