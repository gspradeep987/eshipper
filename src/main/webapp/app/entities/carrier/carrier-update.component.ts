import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICarrier, Carrier } from 'app/shared/model/carrier.model';
import { CarrierService } from './carrier.service';

@Component({
  selector: 'jhi-carrier-update',
  templateUrl: './carrier-update.component.html'
})
export class CarrierUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: []
  });

  constructor(protected carrierService: CarrierService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ carrier }) => {
      this.updateForm(carrier);
    });
  }

  updateForm(carrier: ICarrier): void {
    this.editForm.patchValue({
      id: carrier.id
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const carrier = this.createFromForm();
    if (carrier.id !== undefined) {
      this.subscribeToSaveResponse(this.carrierService.update(carrier));
    } else {
      this.subscribeToSaveResponse(this.carrierService.create(carrier));
    }
  }

  private createFromForm(): ICarrier {
    return {
      ...new Carrier(),
      id: this.editForm.get(['id'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICarrier>>): void {
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
