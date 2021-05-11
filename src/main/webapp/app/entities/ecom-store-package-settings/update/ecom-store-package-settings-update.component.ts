import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IEcomStorePackageSettings, EcomStorePackageSettings } from '../ecom-store-package-settings.model';
import { EcomStorePackageSettingsService } from '../service/ecom-store-package-settings.service';

@Component({
  selector: 'jhi-ecom-store-package-settings-update',
  templateUrl: './ecom-store-package-settings-update.component.html',
})
export class EcomStorePackageSettingsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    packingInfo1: [],
    packingInfo2: [],
    packingInfo3: [],
    packingInfo4: [],
  });

  constructor(
    protected ecomStorePackageSettingsService: EcomStorePackageSettingsService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStorePackageSettings }) => {
      this.updateForm(ecomStorePackageSettings);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomStorePackageSettings = this.createFromForm();
    if (ecomStorePackageSettings.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomStorePackageSettingsService.update(ecomStorePackageSettings));
    } else {
      this.subscribeToSaveResponse(this.ecomStorePackageSettingsService.create(ecomStorePackageSettings));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomStorePackageSettings>>): void {
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

  protected updateForm(ecomStorePackageSettings: IEcomStorePackageSettings): void {
    this.editForm.patchValue({
      id: ecomStorePackageSettings.id,
      packingInfo1: ecomStorePackageSettings.packingInfo1,
      packingInfo2: ecomStorePackageSettings.packingInfo2,
      packingInfo3: ecomStorePackageSettings.packingInfo3,
      packingInfo4: ecomStorePackageSettings.packingInfo4,
    });
  }

  protected createFromForm(): IEcomStorePackageSettings {
    return {
      ...new EcomStorePackageSettings(),
      id: this.editForm.get(['id'])!.value,
      packingInfo1: this.editForm.get(['packingInfo1'])!.value,
      packingInfo2: this.editForm.get(['packingInfo2'])!.value,
      packingInfo3: this.editForm.get(['packingInfo3'])!.value,
      packingInfo4: this.editForm.get(['packingInfo4'])!.value,
    };
  }
}
