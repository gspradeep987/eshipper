import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IWoSalesAgentDetails, WoSalesAgentDetails } from 'app/shared/model/wo-sales-agent-details.model';
import { WoSalesAgentDetailsService } from './wo-sales-agent-details.service';
import { IPaymentMethod } from 'app/shared/model/payment-method.model';
import { PaymentMethodService } from 'app/entities/payment-method/payment-method.service';

@Component({
  selector: 'jhi-wo-sales-agent-details-update',
  templateUrl: './wo-sales-agent-details-update.component.html'
})
export class WoSalesAgentDetailsUpdateComponent implements OnInit {
  isSaving = false;
  paymentmethods: IPaymentMethod[] = [];

  editForm = this.fb.group({
    id: [],
    hstNumber: [],
    promoCode: [],
    promoUrl: [],
    paymentMethodId: []
  });

  constructor(
    protected woSalesAgentDetailsService: WoSalesAgentDetailsService,
    protected paymentMethodService: PaymentMethodService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ woSalesAgentDetails }) => {
      this.updateForm(woSalesAgentDetails);

      this.paymentMethodService.query().subscribe((res: HttpResponse<IPaymentMethod[]>) => (this.paymentmethods = res.body || []));
    });
  }

  updateForm(woSalesAgentDetails: IWoSalesAgentDetails): void {
    this.editForm.patchValue({
      id: woSalesAgentDetails.id,
      hstNumber: woSalesAgentDetails.hstNumber,
      promoCode: woSalesAgentDetails.promoCode,
      promoUrl: woSalesAgentDetails.promoUrl,
      paymentMethodId: woSalesAgentDetails.paymentMethodId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const woSalesAgentDetails = this.createFromForm();
    if (woSalesAgentDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.woSalesAgentDetailsService.update(woSalesAgentDetails));
    } else {
      this.subscribeToSaveResponse(this.woSalesAgentDetailsService.create(woSalesAgentDetails));
    }
  }

  private createFromForm(): IWoSalesAgentDetails {
    return {
      ...new WoSalesAgentDetails(),
      id: this.editForm.get(['id'])!.value,
      hstNumber: this.editForm.get(['hstNumber'])!.value,
      promoCode: this.editForm.get(['promoCode'])!.value,
      promoUrl: this.editForm.get(['promoUrl'])!.value,
      paymentMethodId: this.editForm.get(['paymentMethodId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoSalesAgentDetails>>): void {
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

  trackById(index: number, item: IPaymentMethod): any {
    return item.id;
  }
}
