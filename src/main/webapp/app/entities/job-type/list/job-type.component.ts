import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IJobType } from '../job-type.model';
import { JobTypeService } from '../service/job-type.service';
import { JobTypeDeleteDialogComponent } from '../delete/job-type-delete-dialog.component';

@Component({
  selector: 'jhi-job-type',
  templateUrl: './job-type.component.html',
})
export class JobTypeComponent implements OnInit {
  jobTypes?: IJobType[];
  isLoading = false;

  constructor(protected jobTypeService: JobTypeService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.jobTypeService.query().subscribe(
      (res: HttpResponse<IJobType[]>) => {
        this.isLoading = false;
        this.jobTypes = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IJobType): number {
    return item.id!;
  }

  delete(jobType: IJobType): void {
    const modalRef = this.modalService.open(JobTypeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.jobType = jobType;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
