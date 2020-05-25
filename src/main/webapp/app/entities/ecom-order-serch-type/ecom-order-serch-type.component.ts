import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomOrderSerchType } from 'app/shared/model/ecom-order-serch-type.model';
import { EcomOrderSerchTypeService } from './ecom-order-serch-type.service';
import { EcomOrderSerchTypeDeleteDialogComponent } from './ecom-order-serch-type-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-order-serch-type',
  templateUrl: './ecom-order-serch-type.component.html',
})
export class EcomOrderSerchTypeComponent implements OnInit, OnDestroy {
  ecomOrderSerchTypes?: IEcomOrderSerchType[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomOrderSerchTypeService: EcomOrderSerchTypeService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomOrderSerchTypeService
      .query()
      .subscribe((res: HttpResponse<IEcomOrderSerchType[]>) => (this.ecomOrderSerchTypes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomOrderSerchTypes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomOrderSerchType): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomOrderSerchTypes(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomOrderSerchTypeListModification', () => this.loadAll());
  }

  delete(ecomOrderSerchType: IEcomOrderSerchType): void {
    const modalRef = this.modalService.open(EcomOrderSerchTypeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomOrderSerchType = ecomOrderSerchType;
  }
}
