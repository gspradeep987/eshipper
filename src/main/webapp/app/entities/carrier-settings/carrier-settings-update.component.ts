import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICarrierSettings, CarrierSettings } from 'app/shared/model/carrier-settings.model';
import { CarrierSettingsService } from './carrier-settings.service';

@Component({
  selector: 'jhi-carrier-settings-update',
  templateUrl: './carrier-settings-update.component.html'
})
export class CarrierSettingsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: []
  });

  constructor(
    protected carrierSettingsService: CarrierSettingsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ carrierSettings }) => {
      this.updateForm(carrierSettings);
    });
  }

  updateForm(carrierSettings: ICarrierSettings): void {
    this.editForm.patchValue({
      id: carrierSettings.id
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const carrierSettings = this.createFromForm();
    if (carrierSettings.id !== undefined) {
      this.subscribeToSaveResponse(this.carrierSettingsService.update(carrierSettings));
    } else {
      this.subscribeToSaveResponse(this.carrierSettingsService.create(carrierSettings));
    }
  }

  private createFromForm(): ICarrierSettings {
    return {
      ...new CarrierSettings(),
      id: this.editForm.get(['id'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICarrierSettings>>): void {
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
