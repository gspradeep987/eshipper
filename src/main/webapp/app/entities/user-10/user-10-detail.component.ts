import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUser10 } from 'app/shared/model/user-10.model';

@Component({
  selector: 'jhi-user-10-detail',
  templateUrl: './user-10-detail.component.html',
})
export class User10DetailComponent implements OnInit {
  user10: IUser10 | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ user10 }) => (this.user10 = user10));
  }

  previousState(): void {
    window.history.back();
  }
}
