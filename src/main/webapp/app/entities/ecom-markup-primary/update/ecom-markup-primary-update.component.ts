import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IEcomMarkupPrimary, EcomMarkupPrimary } from '../ecom-markup-primary.model';
import { EcomMarkupPrimaryService } from '../service/ecom-markup-primary.service';

@Component({
  selector: 'jhi-ecom-markup-primary-update',
  templateUrl: './ecom-markup-primary-update.component.html',
})
export class EcomMarkupPrimaryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    value: [],
    fromLane: [null, [Validators.maxLength(50)]],
    toLane: [null, [Validators.maxLength(50)]],
  });

  constructor(
    protected ecomMarkupPrimaryService: EcomMarkupPrimaryService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomMarkupPrimary }) => {
      this.updateForm(ecomMarkupPrimary);
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomMarkupPrimary>>): void {
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

  protected updateForm(ecomMarkupPrimary: IEcomMarkupPrimary): void {
    this.editForm.patchValue({
      id: ecomMarkupPrimary.id,
      value: ecomMarkupPrimary.value,
      fromLane: ecomMarkupPrimary.fromLane,
      toLane: ecomMarkupPrimary.toLane,
    });
  }

  protected createFromForm(): IEcomMarkupPrimary {
    return {
      ...new EcomMarkupPrimary(),
      id: this.editForm.get(['id'])!.value,
      value: this.editForm.get(['value'])!.value,
      fromLane: this.editForm.get(['fromLane'])!.value,
      toLane: this.editForm.get(['toLane'])!.value,
    };
  }
}
