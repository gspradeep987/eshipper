import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IElasticStatus } from 'app/shared/model/elastic-status.model';

@Component({
  selector: 'jhi-elastic-status-detail',
  templateUrl: './elastic-status-detail.component.html',
})
export class ElasticStatusDetailComponent implements OnInit {
  elasticStatus: IElasticStatus | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ elasticStatus }) => (this.elasticStatus = elasticStatus));
  }

  previousState(): void {
    window.history.back();
  }
}
