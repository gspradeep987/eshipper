import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWoSalesCommissionTransfer } from 'app/shared/model/wo-sales-commission-transfer.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { WoSalesCommissionTransferService } from './wo-sales-commission-transfer.service';
import { WoSalesCommissionTransferDeleteDialogComponent } from './wo-sales-commission-transfer-delete-dialog.component';

@Component({
  selector: 'jhi-wo-sales-commission-transfer',
  templateUrl: './wo-sales-commission-transfer.component.html'
})
export class WoSalesCommissionTransferComponent implements OnInit, OnDestroy {
  woSalesCommissionTransfers?: IWoSalesCommissionTransfer[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected woSalesCommissionTransferService: WoSalesCommissionTransferService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.woSalesCommissionTransferService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IWoSalesCommissionTransfer[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInWoSalesCommissionTransfers();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWoSalesCommissionTransfer): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWoSalesCommissionTransfers(): void {
    this.eventSubscriber = this.eventManager.subscribe('woSalesCommissionTransferListModification', () => this.loadPage());
  }

  delete(woSalesCommissionTransfer: IWoSalesCommissionTransfer): void {
    const modalRef = this.modalService.open(WoSalesCommissionTransferDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.woSalesCommissionTransfer = woSalesCommissionTransfer;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IWoSalesCommissionTransfer[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/wo-sales-commission-transfer'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.woSalesCommissionTransfers = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
