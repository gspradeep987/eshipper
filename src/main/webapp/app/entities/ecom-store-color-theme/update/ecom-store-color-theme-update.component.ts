import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IEcomStoreColorTheme, EcomStoreColorTheme } from '../ecom-store-color-theme.model';
import { EcomStoreColorThemeService } from '../service/ecom-store-color-theme.service';

@Component({
  selector: 'jhi-ecom-store-color-theme-update',
  templateUrl: './ecom-store-color-theme-update.component.html',
})
export class EcomStoreColorThemeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    primary: [null, [Validators.maxLength(255)]],
    secondary: [null, [Validators.maxLength(255)]],
  });

  constructor(
    protected ecomStoreColorThemeService: EcomStoreColorThemeService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStoreColorTheme }) => {
      this.updateForm(ecomStoreColorTheme);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomStoreColorTheme = this.createFromForm();
    if (ecomStoreColorTheme.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomStoreColorThemeService.update(ecomStoreColorTheme));
    } else {
      this.subscribeToSaveResponse(this.ecomStoreColorThemeService.create(ecomStoreColorTheme));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomStoreColorTheme>>): void {
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

  protected updateForm(ecomStoreColorTheme: IEcomStoreColorTheme): void {
    this.editForm.patchValue({
      id: ecomStoreColorTheme.id,
      primary: ecomStoreColorTheme.primary,
      secondary: ecomStoreColorTheme.secondary,
    });
  }

  protected createFromForm(): IEcomStoreColorTheme {
    return {
      ...new EcomStoreColorTheme(),
      id: this.editForm.get(['id'])!.value,
      primary: this.editForm.get(['primary'])!.value,
      secondary: this.editForm.get(['secondary'])!.value,
    };
  }
}
