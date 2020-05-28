import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWoSalesOperationalDetails } from 'app/shared/model/wo-sales-operational-details.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { WoSalesOperationalDetailsService } from './wo-sales-operational-details.service';
import { WoSalesOperationalDetailsDeleteDialogComponent } from './wo-sales-operational-details-delete-dialog.component';

@Component({
  selector: 'jhi-wo-sales-operational-details',
  templateUrl: './wo-sales-operational-details.component.html',
})
export class WoSalesOperationalDetailsComponent implements OnInit, OnDestroy {
  woSalesOperationalDetails?: IWoSalesOperationalDetails[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected woSalesOperationalDetailsService: WoSalesOperationalDetailsService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.woSalesOperationalDetailsService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IWoSalesOperationalDetails[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.handleBackNavigation();
    this.registerChangeInWoSalesOperationalDetails();
  }

  handleBackNavigation(): void {
    this.activatedRoute.queryParamMap.subscribe((params: ParamMap) => {
      const prevPage = params.get('page');
      const prevSort = params.get('sort');
      const prevSortSplit = prevSort?.split(',');
      if (prevSortSplit) {
        this.predicate = prevSortSplit[0];
        this.ascending = prevSortSplit[1] === 'asc';
      }
      if (prevPage && +prevPage !== this.page) {
        this.ngbPaginationPage = +prevPage;
        this.loadPage(+prevPage);
      } else {
        this.loadPage(this.page);
      }
    });
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWoSalesOperationalDetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWoSalesOperationalDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('woSalesOperationalDetailsListModification', () => this.loadPage());
  }

  delete(woSalesOperationalDetails: IWoSalesOperationalDetails): void {
    const modalRef = this.modalService.open(WoSalesOperationalDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.woSalesOperationalDetails = woSalesOperationalDetails;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IWoSalesOperationalDetails[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/wo-sales-operational-details'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
      },
    });
    this.woSalesOperationalDetails = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
