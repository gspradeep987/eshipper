import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomOrderAttachment } from 'app/shared/model/ecom-order-attachment.model';
import { EcomOrderAttachmentService } from './ecom-order-attachment.service';
import { EcomOrderAttachmentDeleteDialogComponent } from './ecom-order-attachment-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-order-attachment',
  templateUrl: './ecom-order-attachment.component.html'
})
export class EcomOrderAttachmentComponent implements OnInit, OnDestroy {
  ecomOrderAttachments?: IEcomOrderAttachment[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomOrderAttachmentService: EcomOrderAttachmentService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomOrderAttachmentService
      .query()
      .subscribe((res: HttpResponse<IEcomOrderAttachment[]>) => (this.ecomOrderAttachments = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomOrderAttachments();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomOrderAttachment): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomOrderAttachments(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomOrderAttachmentListModification', () => this.loadAll());
  }

  delete(ecomOrderAttachment: IEcomOrderAttachment): void {
    const modalRef = this.modalService.open(EcomOrderAttachmentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomOrderAttachment = ecomOrderAttachment;
  }
}
