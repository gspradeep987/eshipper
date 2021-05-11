import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISisyphusSubJob } from '../sisyphus-sub-job.model';

@Component({
  selector: 'jhi-sisyphus-sub-job-detail',
  templateUrl: './sisyphus-sub-job-detail.component.html',
})
export class SisyphusSubJobDetailComponent implements OnInit {
  sisyphusSubJob: ISisyphusSubJob | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sisyphusSubJob }) => {
      this.sisyphusSubJob = sisyphusSubJob;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
