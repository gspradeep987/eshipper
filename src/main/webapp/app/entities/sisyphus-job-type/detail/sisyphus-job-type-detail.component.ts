import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISisyphusJobType } from '../sisyphus-job-type.model';

@Component({
  selector: 'jhi-sisyphus-job-type-detail',
  templateUrl: './sisyphus-job-type-detail.component.html',
})
export class SisyphusJobTypeDetailComponent implements OnInit {
  sisyphusJobType: ISisyphusJobType | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sisyphusJobType }) => {
      this.sisyphusJobType = sisyphusJobType;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
