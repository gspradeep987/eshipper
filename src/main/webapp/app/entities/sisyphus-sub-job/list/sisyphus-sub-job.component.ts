import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISisyphusSubJob } from '../sisyphus-sub-job.model';
import { SisyphusSubJobService } from '../service/sisyphus-sub-job.service';
import { SisyphusSubJobDeleteDialogComponent } from '../delete/sisyphus-sub-job-delete-dialog.component';

@Component({
  selector: 'jhi-sisyphus-sub-job',
  templateUrl: './sisyphus-sub-job.component.html',
})
export class SisyphusSubJobComponent implements OnInit {
  sisyphusSubJobs?: ISisyphusSubJob[];
  isLoading = false;

  constructor(protected sisyphusSubJobService: SisyphusSubJobService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.sisyphusSubJobService.query().subscribe(
      (res: HttpResponse<ISisyphusSubJob[]>) => {
        this.isLoading = false;
        this.sisyphusSubJobs = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: ISisyphusSubJob): number {
    return item.id!;
  }

  delete(sisyphusSubJob: ISisyphusSubJob): void {
    const modalRef = this.modalService.open(SisyphusSubJobDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.sisyphusSubJob = sisyphusSubJob;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
