import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISisyphusJob } from '../sisyphus-job.model';
import { SisyphusJobService } from '../service/sisyphus-job.service';
import { SisyphusJobDeleteDialogComponent } from '../delete/sisyphus-job-delete-dialog.component';

@Component({
  selector: 'jhi-sisyphus-job',
  templateUrl: './sisyphus-job.component.html',
})
export class SisyphusJobComponent implements OnInit {
  sisyphusJobs?: ISisyphusJob[];
  isLoading = false;

  constructor(protected sisyphusJobService: SisyphusJobService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.sisyphusJobService.query().subscribe(
      (res: HttpResponse<ISisyphusJob[]>) => {
        this.isLoading = false;
        this.sisyphusJobs = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: ISisyphusJob): number {
    return item.id!;
  }

  delete(sisyphusJob: ISisyphusJob): void {
    const modalRef = this.modalService.open(SisyphusJobDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.sisyphusJob = sisyphusJob;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
