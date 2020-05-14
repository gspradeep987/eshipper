import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWoSalesAgent } from 'app/shared/model/wo-sales-agent.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { WoSalesAgentService } from './wo-sales-agent.service';
import { WoSalesAgentDeleteDialogComponent } from './wo-sales-agent-delete-dialog.component';

@Component({
  selector: 'jhi-wo-sales-agent',
  templateUrl: './wo-sales-agent.component.html'
})
export class WoSalesAgentComponent implements OnInit, OnDestroy {
  woSalesAgents?: IWoSalesAgent[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected woSalesAgentService: WoSalesAgentService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.woSalesAgentService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IWoSalesAgent[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInWoSalesAgents();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWoSalesAgent): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWoSalesAgents(): void {
    this.eventSubscriber = this.eventManager.subscribe('woSalesAgentListModification', () => this.loadPage());
  }

  delete(woSalesAgent: IWoSalesAgent): void {
    const modalRef = this.modalService.open(WoSalesAgentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.woSalesAgent = woSalesAgent;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IWoSalesAgent[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/wo-sales-agent'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.woSalesAgents = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
