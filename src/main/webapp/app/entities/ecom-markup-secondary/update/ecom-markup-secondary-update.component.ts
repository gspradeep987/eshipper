import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IEcomMarkupSecondary, EcomMarkupSecondary } from '../ecom-markup-secondary.model';
import { EcomMarkupSecondaryService } from '../service/ecom-markup-secondary.service';

@Component({
  selector: 'jhi-ecom-markup-secondary-update',
  templateUrl: './ecom-markup-secondary-update.component.html',
})
export class EcomMarkupSecondaryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    value: [],
    fromZip: [null, [Validators.maxLength(50)]],
    toZip: [null, [Validators.maxLength(50)]],
  });

  constructor(
    protected ecomMarkupSecondaryService: EcomMarkupSecondaryService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomMarkupSecondary }) => {
      this.updateForm(ecomMarkupSecondary);
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomMarkupSecondary>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(ecomMarkupSecondary: IEcomMarkupSecondary): void {
    this.editForm.patchValue({
      id: ecomMarkupSecondary.id,
      value: ecomMarkupSecondary.value,
      fromZip: ecomMarkupSecondary.fromZip,
      toZip: ecomMarkupSecondary.toZip,
    });
  }

  protected createFromForm(): IEcomMarkupSecondary {
    return {
      ...new EcomMarkupSecondary(),
      id: this.editForm.get(['id'])!.value,
      value: this.editForm.get(['value'])!.value,
      fromZip: this.editForm.get(['fromZip'])!.value,
      toZip: this.editForm.get(['toZip'])!.value,
    };
  }
}
