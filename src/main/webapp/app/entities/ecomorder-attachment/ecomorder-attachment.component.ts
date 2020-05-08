import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomorderAttachment } from 'app/shared/model/ecomorder-attachment.model';
import { EcomorderAttachmentService } from './ecomorder-attachment.service';
import { EcomorderAttachmentDeleteDialogComponent } from './ecomorder-attachment-delete-dialog.component';

@Component({
  selector: 'jhi-ecomorder-attachment',
  templateUrl: './ecomorder-attachment.component.html'
})
export class EcomorderAttachmentComponent implements OnInit, OnDestroy {
  ecomorderAttachments?: IEcomorderAttachment[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomorderAttachmentService: EcomorderAttachmentService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomorderAttachmentService
      .query()
      .subscribe((res: HttpResponse<IEcomorderAttachment[]>) => (this.ecomorderAttachments = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomorderAttachments();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomorderAttachment): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomorderAttachments(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomorderAttachmentListModification', () => this.loadAll());
  }

  delete(ecomorderAttachment: IEcomorderAttachment): void {
    const modalRef = this.modalService.open(EcomorderAttachmentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomorderAttachment = ecomorderAttachment;
  }
}
