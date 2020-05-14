import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWoSalesOperationalCarrier } from 'app/shared/model/wo-sales-operational-carrier.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { WoSalesOperationalCarrierService } from './wo-sales-operational-carrier.service';
import { WoSalesOperationalCarrierDeleteDialogComponent } from './wo-sales-operational-carrier-delete-dialog.component';

@Component({
  selector: 'jhi-wo-sales-operational-carrier',
  templateUrl: './wo-sales-operational-carrier.component.html'
})
export class WoSalesOperationalCarrierComponent implements OnInit, OnDestroy {
  woSalesOperationalCarriers?: IWoSalesOperationalCarrier[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected woSalesOperationalCarrierService: WoSalesOperationalCarrierService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.woSalesOperationalCarrierService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IWoSalesOperationalCarrier[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInWoSalesOperationalCarriers();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWoSalesOperationalCarrier): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWoSalesOperationalCarriers(): void {
    this.eventSubscriber = this.eventManager.subscribe('woSalesOperationalCarrierListModification', () => this.loadPage());
  }

  delete(woSalesOperationalCarrier: IWoSalesOperationalCarrier): void {
    const modalRef = this.modalService.open(WoSalesOperationalCarrierDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.woSalesOperationalCarrier = woSalesOperationalCarrier;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IWoSalesOperationalCarrier[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/wo-sales-operational-carrier'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.woSalesOperationalCarriers = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
