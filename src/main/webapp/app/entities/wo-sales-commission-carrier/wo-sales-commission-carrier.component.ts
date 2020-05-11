import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWoSalesCommissionCarrier } from 'app/shared/model/wo-sales-commission-carrier.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { WoSalesCommissionCarrierService } from './wo-sales-commission-carrier.service';
import { WoSalesCommissionCarrierDeleteDialogComponent } from './wo-sales-commission-carrier-delete-dialog.component';

@Component({
  selector: 'jhi-wo-sales-commission-carrier',
  templateUrl: './wo-sales-commission-carrier.component.html'
})
export class WoSalesCommissionCarrierComponent implements OnInit, OnDestroy {
  woSalesCommissionCarriers?: IWoSalesCommissionCarrier[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected woSalesCommissionCarrierService: WoSalesCommissionCarrierService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.woSalesCommissionCarrierService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IWoSalesCommissionCarrier[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInWoSalesCommissionCarriers();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWoSalesCommissionCarrier): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWoSalesCommissionCarriers(): void {
    this.eventSubscriber = this.eventManager.subscribe('woSalesCommissionCarrierListModification', () => this.loadPage());
  }

  delete(woSalesCommissionCarrier: IWoSalesCommissionCarrier): void {
    const modalRef = this.modalService.open(WoSalesCommissionCarrierDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.woSalesCommissionCarrier = woSalesCommissionCarrier;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IWoSalesCommissionCarrier[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/wo-sales-commission-carrier'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.woSalesCommissionCarriers = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
