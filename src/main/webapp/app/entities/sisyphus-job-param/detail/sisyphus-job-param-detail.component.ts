import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISisyphusJobParam } from '../sisyphus-job-param.model';

@Component({
  selector: 'jhi-sisyphus-job-param-detail',
  templateUrl: './sisyphus-job-param-detail.component.html',
})
export class SisyphusJobParamDetailComponent implements OnInit {
  sisyphusJobParam: ISisyphusJobParam | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sisyphusJobParam }) => {
      this.sisyphusJobParam = sisyphusJobParam;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
