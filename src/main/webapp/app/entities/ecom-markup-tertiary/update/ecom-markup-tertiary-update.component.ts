import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IEcomMarkupTertiary, EcomMarkupTertiary } from '../ecom-markup-tertiary.model';
import { EcomMarkupTertiaryService } from '../service/ecom-markup-tertiary.service';

@Component({
  selector: 'jhi-ecom-markup-tertiary-update',
  templateUrl: './ecom-markup-tertiary-update.component.html',
})
export class EcomMarkupTertiaryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    wgt1to5: [],
    wgt6to10: [],
    wgt11to15: [],
    wgt16: [],
  });

  constructor(
    protected ecomMarkupTertiaryService: EcomMarkupTertiaryService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomMarkupTertiary }) => {
      this.updateForm(ecomMarkupTertiary);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomMarkupTertiary = this.createFromForm();
    if (ecomMarkupTertiary.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomMarkupTertiaryService.update(ecomMarkupTertiary));
    } else {
      this.subscribeToSaveResponse(this.ecomMarkupTertiaryService.create(ecomMarkupTertiary));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomMarkupTertiary>>): void {
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

  protected updateForm(ecomMarkupTertiary: IEcomMarkupTertiary): void {
    this.editForm.patchValue({
      id: ecomMarkupTertiary.id,
      wgt1to5: ecomMarkupTertiary.wgt1to5,
      wgt6to10: ecomMarkupTertiary.wgt6to10,
      wgt11to15: ecomMarkupTertiary.wgt11to15,
      wgt16: ecomMarkupTertiary.wgt16,
    });
  }

  protected createFromForm(): IEcomMarkupTertiary {
    return {
      ...new EcomMarkupTertiary(),
      id: this.editForm.get(['id'])!.value,
      wgt1to5: this.editForm.get(['wgt1to5'])!.value,
      wgt6to10: this.editForm.get(['wgt6to10'])!.value,
      wgt11to15: this.editForm.get(['wgt11to15'])!.value,
      wgt16: this.editForm.get(['wgt16'])!.value,
    };
  }
}
