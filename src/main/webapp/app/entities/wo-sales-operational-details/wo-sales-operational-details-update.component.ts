import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IWoSalesOperationalDetails, WoSalesOperationalDetails } from 'app/shared/model/wo-sales-operational-details.model';
import { WoSalesOperationalDetailsService } from './wo-sales-operational-details.service';

@Component({
  selector: 'jhi-wo-sales-operational-details-update',
  templateUrl: './wo-sales-operational-details-update.component.html',
})
export class WoSalesOperationalDetailsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    defaultOpExpense: [],
    opExpPalletShip: [],
    opExpPackageShip: [],
    opExpPack: [],
    opExpSmartePost: [],
  });

  constructor(
    protected woSalesOperationalDetailsService: WoSalesOperationalDetailsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ woSalesOperationalDetails }) => {
      this.updateForm(woSalesOperationalDetails);
    });
  }

  updateForm(woSalesOperationalDetails: IWoSalesOperationalDetails): void {
    this.editForm.patchValue({
      id: woSalesOperationalDetails.id,
      defaultOpExpense: woSalesOperationalDetails.defaultOpExpense,
      opExpPalletShip: woSalesOperationalDetails.opExpPalletShip,
      opExpPackageShip: woSalesOperationalDetails.opExpPackageShip,
      opExpPack: woSalesOperationalDetails.opExpPack,
      opExpSmartePost: woSalesOperationalDetails.opExpSmartePost,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const woSalesOperationalDetails = this.createFromForm();
    if (woSalesOperationalDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.woSalesOperationalDetailsService.update(woSalesOperationalDetails));
    } else {
      this.subscribeToSaveResponse(this.woSalesOperationalDetailsService.create(woSalesOperationalDetails));
    }
  }

  private createFromForm(): IWoSalesOperationalDetails {
    return {
      ...new WoSalesOperationalDetails(),
      id: this.editForm.get(['id'])!.value,
      defaultOpExpense: this.editForm.get(['defaultOpExpense'])!.value,
      opExpPalletShip: this.editForm.get(['opExpPalletShip'])!.value,
      opExpPackageShip: this.editForm.get(['opExpPackageShip'])!.value,
      opExpPack: this.editForm.get(['opExpPack'])!.value,
      opExpSmartePost: this.editForm.get(['opExpSmartePost'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoSalesOperationalDetails>>): void {
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
