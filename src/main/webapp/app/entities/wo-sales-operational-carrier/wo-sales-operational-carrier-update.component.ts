import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IWoSalesOperationalCarrier, WoSalesOperationalCarrier } from 'app/shared/model/wo-sales-operational-carrier.model';
import { WoSalesOperationalCarrierService } from './wo-sales-operational-carrier.service';
import { ICarrier } from 'app/shared/model/carrier.model';
import { CarrierService } from 'app/entities/carrier/carrier.service';
import { ICarrierService } from 'app/shared/model/carrier-service.model';
import { CarrierServiceService } from 'app/entities/carrier-service/carrier-service.service';
import { IWoSalesOperationalDetails } from 'app/shared/model/wo-sales-operational-details.model';
import { WoSalesOperationalDetailsService } from 'app/entities/wo-sales-operational-details/wo-sales-operational-details.service';

type SelectableEntity = ICarrier | ICarrierService | IWoSalesOperationalDetails;

@Component({
  selector: 'jhi-wo-sales-operational-carrier-update',
  templateUrl: './wo-sales-operational-carrier-update.component.html'
})
export class WoSalesOperationalCarrierUpdateComponent implements OnInit {
  isSaving = false;
  carriers: ICarrier[] = [];
  carrierservices: ICarrierService[] = [];
  wosalesoperationaldetails: IWoSalesOperationalDetails[] = [];

  editForm = this.fb.group({
    id: [],
    opExp: [],
    carrierId: [],
    carrierServiceId: [],
    woSalesOperationalDetailsId: []
  });

  constructor(
    protected woSalesOperationalCarrierService: WoSalesOperationalCarrierService,
    protected carrierService: CarrierService,
    protected carrierServiceService: CarrierServiceService,
    protected woSalesOperationalDetailsService: WoSalesOperationalDetailsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ woSalesOperationalCarrier }) => {
      this.updateForm(woSalesOperationalCarrier);

      this.carrierService.query().subscribe((res: HttpResponse<ICarrier[]>) => (this.carriers = res.body || []));

      this.carrierServiceService.query().subscribe((res: HttpResponse<ICarrierService[]>) => (this.carrierservices = res.body || []));

      this.woSalesOperationalDetailsService
        .query()
        .subscribe((res: HttpResponse<IWoSalesOperationalDetails[]>) => (this.wosalesoperationaldetails = res.body || []));
    });
  }

  updateForm(woSalesOperationalCarrier: IWoSalesOperationalCarrier): void {
    this.editForm.patchValue({
      id: woSalesOperationalCarrier.id,
      opExp: woSalesOperationalCarrier.opExp,
      carrierId: woSalesOperationalCarrier.carrierId,
      carrierServiceId: woSalesOperationalCarrier.carrierServiceId,
      woSalesOperationalDetailsId: woSalesOperationalCarrier.woSalesOperationalDetailsId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const woSalesOperationalCarrier = this.createFromForm();
    if (woSalesOperationalCarrier.id !== undefined) {
      this.subscribeToSaveResponse(this.woSalesOperationalCarrierService.update(woSalesOperationalCarrier));
    } else {
      this.subscribeToSaveResponse(this.woSalesOperationalCarrierService.create(woSalesOperationalCarrier));
    }
  }

  private createFromForm(): IWoSalesOperationalCarrier {
    return {
      ...new WoSalesOperationalCarrier(),
      id: this.editForm.get(['id'])!.value,
      opExp: this.editForm.get(['opExp'])!.value,
      carrierId: this.editForm.get(['carrierId'])!.value,
      carrierServiceId: this.editForm.get(['carrierServiceId'])!.value,
      woSalesOperationalDetailsId: this.editForm.get(['woSalesOperationalDetailsId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoSalesOperationalCarrier>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
