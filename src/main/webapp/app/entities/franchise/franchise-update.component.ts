import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFranchise, Franchise } from 'app/shared/model/franchise.model';
import { FranchiseService } from './franchise.service';

@Component({
  selector: 'jhi-franchise-update',
  templateUrl: './franchise-update.component.html'
})
export class FranchiseUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: []
  });

  constructor(protected franchiseService: FranchiseService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ franchise }) => {
      this.updateForm(franchise);
    });
  }

  updateForm(franchise: IFranchise): void {
    this.editForm.patchValue({
      id: franchise.id
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const franchise = this.createFromForm();
    if (franchise.id !== undefined) {
      this.subscribeToSaveResponse(this.franchiseService.update(franchise));
    } else {
      this.subscribeToSaveResponse(this.franchiseService.create(franchise));
    }
  }

  private createFromForm(): IFranchise {
    return {
      ...new Franchise(),
      id: this.editForm.get(['id'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFranchise>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
