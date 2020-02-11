import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IShipmentService, ShipmentService } from 'app/shared/model/shipment-service.model';
import { ShipmentServiceService } from './shipment-service.service';

@Component({
  selector: 'jhi-shipment-service-update',
  templateUrl: './shipment-service-update.component.html'
})
export class ShipmentServiceUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]]
  });

  constructor(
    protected shipmentServiceService: ShipmentServiceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shipmentService }) => {
      this.updateForm(shipmentService);
    });
  }

  updateForm(shipmentService: IShipmentService): void {
    this.editForm.patchValue({
      id: shipmentService.id,
      name: shipmentService.name
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const shipmentService = this.createFromForm();
    if (shipmentService.id !== undefined) {
      this.subscribeToSaveResponse(this.shipmentServiceService.update(shipmentService));
    } else {
      this.subscribeToSaveResponse(this.shipmentServiceService.create(shipmentService));
    }
  }

  private createFromForm(): IShipmentService {
    return {
      ...new ShipmentService(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShipmentService>>): void {
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
