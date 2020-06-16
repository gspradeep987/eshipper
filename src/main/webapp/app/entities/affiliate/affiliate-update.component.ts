import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAffiliate, Affiliate } from 'app/shared/model/affiliate.model';
import { AffiliateService } from './affiliate.service';
import { ICompany } from 'app/shared/model/company.model';
import { CompanyService } from 'app/entities/company/company.service';
import { IPaymentMethod } from 'app/shared/model/payment-method.model';
import { PaymentMethodService } from 'app/entities/payment-method/payment-method.service';
import { IFranchise } from 'app/shared/model/franchise.model';
import { FranchiseService } from 'app/entities/franchise/franchise.service';

type SelectableEntity = IPaymentMethod | ICompany | IFranchise;

@Component({
  selector: 'jhi-affiliate-update',
  templateUrl: './affiliate-update.component.html',
})
export class AffiliateUpdateComponent implements OnInit {
  isSaving = false;
  paymentmethods: IPaymentMethod[] = [];
  companies: ICompany[] = [];
  franchises: IFranchise[] = [];
  commissionDateDp: any;
  createdDateDp: any;
  updatedDateDp: any;

  editForm = this.fb.group({
    id: [],
    isActive: [],
    notifyUser: [],
    promoCode: [],
    promoCodeUrl: [],
    commissionPercentage: [],
    commissionDate: [],
    createdDate: [],
    updatedDate: [],
    paymentMethodId: [],
    affiliateId: [],
    franchiseId: [],
  });

  constructor(
    protected affiliateService: AffiliateService,
    protected companyService: CompanyService,
    protected paymentMethodService: PaymentMethodService,
    protected franchiseService: FranchiseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ affiliate }) => {
      this.updateForm(affiliate);

      this.paymentMethodService.query().subscribe((res: HttpResponse<IPaymentMethod[]>) => (this.paymentmethods = res.body || []));

      this.companyService.query().subscribe((res: HttpResponse<ICompany[]>) => (this.companies = res.body || []));

      this.franchiseService.query().subscribe((res: HttpResponse<IFranchise[]>) => (this.franchises = res.body || []));
    });
  }

  updateForm(affiliate: IAffiliate): void {
    this.editForm.patchValue({
      id: affiliate.id,
      isActive: affiliate.isActive,
      notifyUser: affiliate.notifyUser,
      promoCode: affiliate.promoCode,
      promoCodeUrl: affiliate.promoCodeUrl,
      commissionPercentage: affiliate.commissionPercentage,
      commissionDate: affiliate.commissionDate,
      createdDate: affiliate.createdDate,
      updatedDate: affiliate.updatedDate,
      paymentMethodId: affiliate.paymentMethodId,
      affiliateId: affiliate.affiliateId,
      franchiseId: affiliate.franchiseId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const affiliate = this.createFromForm();
    if (affiliate.id !== undefined) {
      this.subscribeToSaveResponse(this.affiliateService.update(affiliate));
    } else {
      this.subscribeToSaveResponse(this.affiliateService.create(affiliate));
    }
  }

  private createFromForm(): IAffiliate {
    return {
      ...new Affiliate(),
      id: this.editForm.get(['id'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
      notifyUser: this.editForm.get(['notifyUser'])!.value,
      promoCode: this.editForm.get(['promoCode'])!.value,
      promoCodeUrl: this.editForm.get(['promoCodeUrl'])!.value,
      commissionPercentage: this.editForm.get(['commissionPercentage'])!.value,
      commissionDate: this.editForm.get(['commissionDate'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value,
      updatedDate: this.editForm.get(['updatedDate'])!.value,
      paymentMethodId: this.editForm.get(['paymentMethodId'])!.value,
      affiliateId: this.editForm.get(['affiliateId'])!.value,
      franchiseId: this.editForm.get(['franchiseId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAffiliate>>): void {
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
