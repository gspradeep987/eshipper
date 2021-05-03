import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISisyphusClasses } from '../sisyphus-classes.model';

@Component({
  selector: 'jhi-sisyphus-classes-detail',
  templateUrl: './sisyphus-classes-detail.component.html',
})
export class SisyphusClassesDetailComponent implements OnInit {
  sisyphusClasses: ISisyphusClasses | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sisyphusClasses }) => {
      this.sisyphusClasses = sisyphusClasses;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
