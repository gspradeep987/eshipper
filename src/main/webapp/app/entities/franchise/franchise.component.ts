import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFranchise } from 'app/shared/model/franchise.model';
import { FranchiseService } from './franchise.service';
import { FranchiseDeleteDialogComponent } from './franchise-delete-dialog.component';

@Component({
  selector: 'jhi-franchise',
  templateUrl: './franchise.component.html'
})
export class FranchiseComponent implements OnInit, OnDestroy {
  franchises?: IFranchise[];
  eventSubscriber?: Subscription;

  constructor(protected franchiseService: FranchiseService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.franchiseService.query().subscribe((res: HttpResponse<IFranchise[]>) => (this.franchises = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFranchises();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFranchise): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFranchises(): void {
    this.eventSubscriber = this.eventManager.subscribe('franchiseListModification', () => this.loadAll());
  }

  delete(franchise: IFranchise): void {
    const modalRef = this.modalService.open(FranchiseDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.franchise = franchise;
  }
}
