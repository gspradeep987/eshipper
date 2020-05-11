import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICarrierService, CarrierService } from 'app/shared/model/carrier-service.model';
import { CarrierServiceService } from './carrier-service.service';

@Component({
  selector: 'jhi-carrier-service-update',
  templateUrl: './carrier-service-update.component.html'
})
export class CarrierServiceUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: []
  });

  constructor(protected carrierServiceService: CarrierServiceService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ carrierService }) => {
      this.updateForm(carrierService);
    });
  }

  updateForm(carrierService: ICarrierService): void {
    this.editForm.patchValue({
      id: carrierService.id
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const carrierService = this.createFromForm();
    if (carrierService.id !== undefined) {
      this.subscribeToSaveResponse(this.carrierServiceService.update(carrierService));
    } else {
      this.subscribeToSaveResponse(this.carrierServiceService.create(carrierService));
    }
  }

  private createFromForm(): ICarrierService {
    return {
      ...new CarrierService(),
      id: this.editForm.get(['id'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICarrierService>>): void {
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
