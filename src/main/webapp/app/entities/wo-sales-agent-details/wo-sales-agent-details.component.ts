import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWoSalesAgentDetails } from 'app/shared/model/wo-sales-agent-details.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { WoSalesAgentDetailsService } from './wo-sales-agent-details.service';
import { WoSalesAgentDetailsDeleteDialogComponent } from './wo-sales-agent-details-delete-dialog.component';

@Component({
  selector: 'jhi-wo-sales-agent-details',
  templateUrl: './wo-sales-agent-details.component.html',
})
export class WoSalesAgentDetailsComponent implements OnInit, OnDestroy {
  woSalesAgentDetails?: IWoSalesAgentDetails[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected woSalesAgentDetailsService: WoSalesAgentDetailsService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.woSalesAgentDetailsService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IWoSalesAgentDetails[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInWoSalesAgentDetails();
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

  trackId(index: number, item: IWoSalesAgentDetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWoSalesAgentDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('woSalesAgentDetailsListModification', () => this.loadPage());
  }

  delete(woSalesAgentDetails: IWoSalesAgentDetails): void {
    const modalRef = this.modalService.open(WoSalesAgentDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.woSalesAgentDetails = woSalesAgentDetails;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IWoSalesAgentDetails[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/wo-sales-agent-details'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
      },
    });
    this.woSalesAgentDetails = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
