import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IWoSalesCommissionTransfer, WoSalesCommissionTransfer } from 'app/shared/model/wo-sales-commission-transfer.model';
import { WoSalesCommissionTransferService } from './wo-sales-commission-transfer.service';

@Component({
  selector: 'jhi-wo-sales-commission-transfer-update',
  templateUrl: './wo-sales-commission-transfer-update.component.html'
})
export class WoSalesCommissionTransferUpdateComponent implements OnInit {
  isSaving = false;
  customerTransferDateDp: any;

  editForm = this.fb.group({
    id: [],
    customerTransferDate: [],
    isIncludeHistoricalData: []
  });

  constructor(
    protected woSalesCommissionTransferService: WoSalesCommissionTransferService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ woSalesCommissionTransfer }) => {
      this.updateForm(woSalesCommissionTransfer);
    });
  }

  updateForm(woSalesCommissionTransfer: IWoSalesCommissionTransfer): void {
    this.editForm.patchValue({
      id: woSalesCommissionTransfer.id,
      customerTransferDate: woSalesCommissionTransfer.customerTransferDate,
      isIncludeHistoricalData: woSalesCommissionTransfer.isIncludeHistoricalData
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const woSalesCommissionTransfer = this.createFromForm();
    if (woSalesCommissionTransfer.id !== undefined) {
      this.subscribeToSaveResponse(this.woSalesCommissionTransferService.update(woSalesCommissionTransfer));
    } else {
      this.subscribeToSaveResponse(this.woSalesCommissionTransferService.create(woSalesCommissionTransfer));
    }
  }

  private createFromForm(): IWoSalesCommissionTransfer {
    return {
      ...new WoSalesCommissionTransfer(),
      id: this.editForm.get(['id'])!.value,
      customerTransferDate: this.editForm.get(['customerTransferDate'])!.value,
      isIncludeHistoricalData: this.editForm.get(['isIncludeHistoricalData'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoSalesCommissionTransfer>>): void {
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
