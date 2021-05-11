import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISisyphusClient } from '../sisyphus-client.model';

@Component({
  selector: 'jhi-sisyphus-client-detail',
  templateUrl: './sisyphus-client-detail.component.html',
})
export class SisyphusClientDetailComponent implements OnInit {
  sisyphusClient: ISisyphusClient | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sisyphusClient }) => {
      this.sisyphusClient = sisyphusClient;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
