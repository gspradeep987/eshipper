import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IShipmentService, ShipmentService } from '../shipment-service.model';
import { ShipmentServiceService } from '../service/shipment-service.service';

@Component({
  selector: 'jhi-shipment-service-update',
  templateUrl: './shipment-service-update.component.html',
})
export class ShipmentServiceUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]],
  });

  constructor(
    protected shipmentServiceService: ShipmentServiceService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shipmentService }) => {
      this.updateForm(shipmentService);
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShipmentService>>): void {
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

  protected updateForm(shipmentService: IShipmentService): void {
    this.editForm.patchValue({
      id: shipmentService.id,
      name: shipmentService.name,
    });
  }

  protected createFromForm(): IShipmentService {
    return {
      ...new ShipmentService(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
    };
  }
}
