import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFranchise } from 'app/shared/model/franchise.model';

@Component({
  selector: 'jhi-franchise-detail',
  templateUrl: './franchise-detail.component.html'
})
export class FranchiseDetailComponent implements OnInit {
  franchise: IFranchise | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ franchise }) => (this.franchise = franchise));
  }

  previousState(): void {
    window.history.back();
  }
}
