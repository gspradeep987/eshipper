import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IWoSalesCommissionCarrier, WoSalesCommissionCarrier } from 'app/shared/model/wo-sales-commission-carrier.model';
import { WoSalesCommissionCarrierService } from './wo-sales-commission-carrier.service';
import { ICarrier } from 'app/shared/model/carrier.model';
import { CarrierService } from 'app/entities/carrier/carrier.service';
import { ICarrierService } from 'app/shared/model/carrier-service.model';
import { CarrierServiceService } from 'app/entities/carrier-service/carrier-service.service';
import { IWoSalesCommissionDetails } from 'app/shared/model/wo-sales-commission-details.model';
import { WoSalesCommissionDetailsService } from 'app/entities/wo-sales-commission-details/wo-sales-commission-details.service';

type SelectableEntity = ICarrier | ICarrierService | IWoSalesCommissionDetails;

@Component({
  selector: 'jhi-wo-sales-commission-carrier-update',
  templateUrl: './wo-sales-commission-carrier-update.component.html',
})
export class WoSalesCommissionCarrierUpdateComponent implements OnInit {
  isSaving = false;
  carriers: ICarrier[] = [];
  carrierservices: ICarrierService[] = [];
  wosalescommissiondetails: IWoSalesCommissionDetails[] = [];

  editForm = this.fb.group({
    id: [],
    commissionPercentageByCarrier: [],
    carrierId: [],
    carrierServiceId: [],
    woSalesCommissionDetailsId: [],
  });

  constructor(
    protected woSalesCommissionCarrierService: WoSalesCommissionCarrierService,
    protected carrierService: CarrierService,
    protected carrierServiceService: CarrierServiceService,
    protected woSalesCommissionDetailsService: WoSalesCommissionDetailsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ woSalesCommissionCarrier }) => {
      this.updateForm(woSalesCommissionCarrier);

      this.carrierService.query().subscribe((res: HttpResponse<ICarrier[]>) => (this.carriers = res.body || []));

      this.carrierServiceService.query().subscribe((res: HttpResponse<ICarrierService[]>) => (this.carrierservices = res.body || []));

      this.woSalesCommissionDetailsService
        .query()
        .subscribe((res: HttpResponse<IWoSalesCommissionDetails[]>) => (this.wosalescommissiondetails = res.body || []));
    });
  }

  updateForm(woSalesCommissionCarrier: IWoSalesCommissionCarrier): void {
    this.editForm.patchValue({
      id: woSalesCommissionCarrier.id,
      commissionPercentageByCarrier: woSalesCommissionCarrier.commissionPercentageByCarrier,
      carrierId: woSalesCommissionCarrier.carrierId,
      carrierServiceId: woSalesCommissionCarrier.carrierServiceId,
      woSalesCommissionDetailsId: woSalesCommissionCarrier.woSalesCommissionDetailsId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const woSalesCommissionCarrier = this.createFromForm();
    if (woSalesCommissionCarrier.id !== undefined) {
      this.subscribeToSaveResponse(this.woSalesCommissionCarrierService.update(woSalesCommissionCarrier));
    } else {
      this.subscribeToSaveResponse(this.woSalesCommissionCarrierService.create(woSalesCommissionCarrier));
    }
  }

  private createFromForm(): IWoSalesCommissionCarrier {
    return {
      ...new WoSalesCommissionCarrier(),
      id: this.editForm.get(['id'])!.value,
      commissionPercentageByCarrier: this.editForm.get(['commissionPercentageByCarrier'])!.value,
      carrierId: this.editForm.get(['carrierId'])!.value,
      carrierServiceId: this.editForm.get(['carrierServiceId'])!.value,
      woSalesCommissionDetailsId: this.editForm.get(['woSalesCommissionDetailsId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoSalesCommissionCarrier>>): void {
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
