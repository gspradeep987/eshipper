import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IElasticSearchWoSalesAgent } from 'app/shared/model/elastic-search-wo-sales-agent.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ElasticSearchWoSalesAgentService } from './elastic-search-wo-sales-agent.service';
import { ElasticSearchWoSalesAgentDeleteDialogComponent } from './elastic-search-wo-sales-agent-delete-dialog.component';

@Component({
  selector: 'jhi-elastic-search-wo-sales-agent',
  templateUrl: './elastic-search-wo-sales-agent.component.html',
})
export class ElasticSearchWoSalesAgentComponent implements OnInit, OnDestroy {
  elasticSearchWoSalesAgents?: IElasticSearchWoSalesAgent[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected elasticSearchWoSalesAgentService: ElasticSearchWoSalesAgentService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.elasticSearchWoSalesAgentService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IElasticSearchWoSalesAgent[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInElasticSearchWoSalesAgents();
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

  trackId(index: number, item: IElasticSearchWoSalesAgent): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInElasticSearchWoSalesAgents(): void {
    this.eventSubscriber = this.eventManager.subscribe('elasticSearchWoSalesAgentListModification', () => this.loadPage());
  }

  delete(elasticSearchWoSalesAgent: IElasticSearchWoSalesAgent): void {
    const modalRef = this.modalService.open(ElasticSearchWoSalesAgentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.elasticSearchWoSalesAgent = elasticSearchWoSalesAgent;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IElasticSearchWoSalesAgent[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/elastic-search-wo-sales-agent'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
      },
    });
    this.elasticSearchWoSalesAgents = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
