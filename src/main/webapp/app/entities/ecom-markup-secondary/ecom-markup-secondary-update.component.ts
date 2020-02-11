import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomMarkupSecondary, EcomMarkupSecondary } from 'app/shared/model/ecom-markup-secondary.model';
import { EcomMarkupSecondaryService } from './ecom-markup-secondary.service';

@Component({
  selector: 'jhi-ecom-markup-secondary-update',
  templateUrl: './ecom-markup-secondary-update.component.html'
})
export class EcomMarkupSecondaryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    value: [],
    fromZip: [null, [Validators.maxLength(50)]],
    toZip: [null, [Validators.maxLength(50)]]
  });

  constructor(
    protected ecomMarkupSecondaryService: EcomMarkupSecondaryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomMarkupSecondary }) => {
      this.updateForm(ecomMarkupSecondary);
    });
  }

  updateForm(ecomMarkupSecondary: IEcomMarkupSecondary): void {
    this.editForm.patchValue({
      id: ecomMarkupSecondary.id,
      value: ecomMarkupSecondary.value,
      fromZip: ecomMarkupSecondary.fromZip,
      toZip: ecomMarkupSecondary.toZip
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomMarkupSecondary = this.createFromForm();
    if (ecomMarkupSecondary.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomMarkupSecondaryService.update(ecomMarkupSecondary));
    } else {
      this.subscribeToSaveResponse(this.ecomMarkupSecondaryService.create(ecomMarkupSecondary));
    }
  }

  private createFromForm(): IEcomMarkupSecondary {
    return {
      ...new EcomMarkupSecondary(),
      id: this.editForm.get(['id'])!.value,
      value: this.editForm.get(['value'])!.value,
      fromZip: this.editForm.get(['fromZip'])!.value,
      toZip: this.editForm.get(['toZip'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomMarkupSecondary>>): void {
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
