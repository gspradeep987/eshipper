import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISignatureRequired } from 'app/shared/model/signature-required.model';
import { SignatureRequiredService } from './signature-required.service';
import { SignatureRequiredDeleteDialogComponent } from './signature-required-delete-dialog.component';

@Component({
  selector: 'jhi-signature-required',
  templateUrl: './signature-required.component.html'
})
export class SignatureRequiredComponent implements OnInit, OnDestroy {
  signatureRequireds?: ISignatureRequired[];
  eventSubscriber?: Subscription;

  constructor(
    protected signatureRequiredService: SignatureRequiredService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.signatureRequiredService
      .query()
      .subscribe((res: HttpResponse<ISignatureRequired[]>) => (this.signatureRequireds = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSignatureRequireds();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISignatureRequired): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSignatureRequireds(): void {
    this.eventSubscriber = this.eventManager.subscribe('signatureRequiredListModification', () => this.loadAll());
  }

  delete(signatureRequired: ISignatureRequired): void {
    const modalRef = this.modalService.open(SignatureRequiredDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.signatureRequired = signatureRequired;
  }
}
