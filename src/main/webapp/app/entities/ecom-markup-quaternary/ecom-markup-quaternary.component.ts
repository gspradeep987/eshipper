import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomMarkupQuaternary } from 'app/shared/model/ecom-markup-quaternary.model';
import { EcomMarkupQuaternaryService } from './ecom-markup-quaternary.service';
import { EcomMarkupQuaternaryDeleteDialogComponent } from './ecom-markup-quaternary-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-markup-quaternary',
  templateUrl: './ecom-markup-quaternary.component.html'
})
export class EcomMarkupQuaternaryComponent implements OnInit, OnDestroy {
  ecomMarkupQuaternaries?: IEcomMarkupQuaternary[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomMarkupQuaternaryService: EcomMarkupQuaternaryService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomMarkupQuaternaryService
      .query()
      .subscribe((res: HttpResponse<IEcomMarkupQuaternary[]>) => (this.ecomMarkupQuaternaries = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomMarkupQuaternaries();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomMarkupQuaternary): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomMarkupQuaternaries(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomMarkupQuaternaryListModification', () => this.loadAll());
  }

  delete(ecomMarkupQuaternary: IEcomMarkupQuaternary): void {
    const modalRef = this.modalService.open(EcomMarkupQuaternaryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomMarkupQuaternary = ecomMarkupQuaternary;
  }
}
