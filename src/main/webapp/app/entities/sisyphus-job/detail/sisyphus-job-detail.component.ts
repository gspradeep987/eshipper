import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISisyphusJob } from '../sisyphus-job.model';

@Component({
  selector: 'jhi-sisyphus-job-detail',
  templateUrl: './sisyphus-job-detail.component.html',
})
export class SisyphusJobDetailComponent implements OnInit {
  sisyphusJob: ISisyphusJob | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sisyphusJob }) => {
      this.sisyphusJob = sisyphusJob;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
