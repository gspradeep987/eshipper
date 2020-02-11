import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomMarkupSecondary } from 'app/shared/model/ecom-markup-secondary.model';
import { EcomMarkupSecondaryService } from './ecom-markup-secondary.service';
import { EcomMarkupSecondaryDeleteDialogComponent } from './ecom-markup-secondary-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-markup-secondary',
  templateUrl: './ecom-markup-secondary.component.html'
})
export class EcomMarkupSecondaryComponent implements OnInit, OnDestroy {
  ecomMarkupSecondaries?: IEcomMarkupSecondary[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomMarkupSecondaryService: EcomMarkupSecondaryService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomMarkupSecondaryService
      .query()
      .subscribe((res: HttpResponse<IEcomMarkupSecondary[]>) => (this.ecomMarkupSecondaries = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomMarkupSecondaries();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomMarkupSecondary): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomMarkupSecondaries(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomMarkupSecondaryListModification', () => this.loadAll());
  }

  delete(ecomMarkupSecondary: IEcomMarkupSecondary): void {
    const modalRef = this.modalService.open(EcomMarkupSecondaryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomMarkupSecondary = ecomMarkupSecondary;
  }
}
