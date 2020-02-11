import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomMarkupPrimary } from 'app/shared/model/ecom-markup-primary.model';
import { EcomMarkupPrimaryService } from './ecom-markup-primary.service';
import { EcomMarkupPrimaryDeleteDialogComponent } from './ecom-markup-primary-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-markup-primary',
  templateUrl: './ecom-markup-primary.component.html'
})
export class EcomMarkupPrimaryComponent implements OnInit, OnDestroy {
  ecomMarkupPrimaries?: IEcomMarkupPrimary[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomMarkupPrimaryService: EcomMarkupPrimaryService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomMarkupPrimaryService
      .query()
      .subscribe((res: HttpResponse<IEcomMarkupPrimary[]>) => (this.ecomMarkupPrimaries = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomMarkupPrimaries();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomMarkupPrimary): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomMarkupPrimaries(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomMarkupPrimaryListModification', () => this.loadAll());
  }

  delete(ecomMarkupPrimary: IEcomMarkupPrimary): void {
    const modalRef = this.modalService.open(EcomMarkupPrimaryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomMarkupPrimary = ecomMarkupPrimary;
  }
}
