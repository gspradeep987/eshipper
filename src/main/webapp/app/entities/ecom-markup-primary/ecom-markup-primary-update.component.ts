import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomMarkupPrimary, EcomMarkupPrimary } from 'app/shared/model/ecom-markup-primary.model';
import { EcomMarkupPrimaryService } from './ecom-markup-primary.service';

@Component({
  selector: 'jhi-ecom-markup-primary-update',
  templateUrl: './ecom-markup-primary-update.component.html'
})
export class EcomMarkupPrimaryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    value: [],
    fromLane: [null, [Validators.maxLength(50)]],
    toLane: [null, [Validators.maxLength(50)]]
  });

  constructor(
    protected ecomMarkupPrimaryService: EcomMarkupPrimaryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomMarkupPrimary }) => {
      this.updateForm(ecomMarkupPrimary);
    });
  }

  updateForm(ecomMarkupPrimary: IEcomMarkupPrimary): void {
    this.editForm.patchValue({
      id: ecomMarkupPrimary.id,
      value: ecomMarkupPrimary.value,
      fromLane: ecomMarkupPrimary.fromLane,
      toLane: ecomMarkupPrimary.toLane
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomMarkupPrimary = this.createFromForm();
    if (ecomMarkupPrimary.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomMarkupPrimaryService.update(ecomMarkupPrimary));
    } else {
      this.subscribeToSaveResponse(this.ecomMarkupPrimaryService.create(ecomMarkupPrimary));
    }
  }

  private createFromForm(): IEcomMarkupPrimary {
    return {
      ...new EcomMarkupPrimary(),
      id: this.editForm.get(['id'])!.value,
      value: this.editForm.get(['value'])!.value,
      fromLane: this.editForm.get(['fromLane'])!.value,
      toLane: this.editForm.get(['toLane'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomMarkupPrimary>>): void {
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
