import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IWoSalesCommissionDetails, WoSalesCommissionDetails } from 'app/shared/model/wo-sales-commission-details.model';
import { WoSalesCommissionDetailsService } from './wo-sales-commission-details.service';

@Component({
  selector: 'jhi-wo-sales-commission-details-update',
  templateUrl: './wo-sales-commission-details-update.component.html',
})
export class WoSalesCommissionDetailsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    commission: [],
  });

  constructor(
    protected woSalesCommissionDetailsService: WoSalesCommissionDetailsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ woSalesCommissionDetails }) => {
      this.updateForm(woSalesCommissionDetails);
    });
  }

  updateForm(woSalesCommissionDetails: IWoSalesCommissionDetails): void {
    this.editForm.patchValue({
      id: woSalesCommissionDetails.id,
      commission: woSalesCommissionDetails.commission,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const woSalesCommissionDetails = this.createFromForm();
    if (woSalesCommissionDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.woSalesCommissionDetailsService.update(woSalesCommissionDetails));
    } else {
      this.subscribeToSaveResponse(this.woSalesCommissionDetailsService.create(woSalesCommissionDetails));
    }
  }

  private createFromForm(): IWoSalesCommissionDetails {
    return {
      ...new WoSalesCommissionDetails(),
      id: this.editForm.get(['id'])!.value,
      commission: this.editForm.get(['commission'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoSalesCommissionDetails>>): void {
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
