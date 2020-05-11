import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPaymentMethod, PaymentMethod } from 'app/shared/model/payment-method.model';
import { PaymentMethodService } from './payment-method.service';
import { IWoSalesAgentDetails } from 'app/shared/model/wo-sales-agent-details.model';
import { WoSalesAgentDetailsService } from 'app/entities/wo-sales-agent-details/wo-sales-agent-details.service';

@Component({
  selector: 'jhi-payment-method-update',
  templateUrl: './payment-method-update.component.html'
})
export class PaymentMethodUpdateComponent implements OnInit {
  isSaving = false;
  wosalesagentdetails: IWoSalesAgentDetails[] = [];

  editForm = this.fb.group({
    id: [],
    woSalesAgentDetailsId: []
  });

  constructor(
    protected paymentMethodService: PaymentMethodService,
    protected woSalesAgentDetailsService: WoSalesAgentDetailsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paymentMethod }) => {
      this.updateForm(paymentMethod);

      this.woSalesAgentDetailsService
        .query()
        .subscribe((res: HttpResponse<IWoSalesAgentDetails[]>) => (this.wosalesagentdetails = res.body || []));
    });
  }

  updateForm(paymentMethod: IPaymentMethod): void {
    this.editForm.patchValue({
      id: paymentMethod.id,
      woSalesAgentDetailsId: paymentMethod.woSalesAgentDetailsId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paymentMethod = this.createFromForm();
    if (paymentMethod.id !== undefined) {
      this.subscribeToSaveResponse(this.paymentMethodService.update(paymentMethod));
    } else {
      this.subscribeToSaveResponse(this.paymentMethodService.create(paymentMethod));
    }
  }

  private createFromForm(): IPaymentMethod {
    return {
      ...new PaymentMethod(),
      id: this.editForm.get(['id'])!.value,
      woSalesAgentDetailsId: this.editForm.get(['woSalesAgentDetailsId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaymentMethod>>): void {
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

  trackById(index: number, item: IWoSalesAgentDetails): any {
    return item.id;
  }
}
