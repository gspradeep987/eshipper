import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICompanyCarrierAccount, CompanyCarrierAccount } from 'app/shared/model/company-carrier-account.model';
import { CompanyCarrierAccountService } from './company-carrier-account.service';
import { ICountry } from 'app/shared/model/country.model';
import { CountryService } from 'app/entities/country/country.service';
import { ICarrier } from 'app/shared/model/carrier.model';
import { CarrierService } from 'app/entities/carrier/carrier.service';
import { IFranchise } from 'app/shared/model/franchise.model';
import { FranchiseService } from 'app/entities/franchise/franchise.service';

type SelectableEntity = ICountry | ICarrier | IFranchise;

@Component({
  selector: 'jhi-company-carrier-account-update',
  templateUrl: './company-carrier-account-update.component.html'
})
export class CompanyCarrierAccountUpdateComponent implements OnInit {
  isSaving = false;
  countries: ICountry[] = [];
  carriers: ICarrier[] = [];
  franchises: IFranchise[] = [];

  editForm = this.fb.group({
    id: [],
    accountOwner: [],
    accountNumber: [],
    meterNumber: [null, [Validators.maxLength(30)]],
    key: [null, [Validators.maxLength(30)]],
    password: [null, [Validators.maxLength(30)]],
    regionId: [],
    carrierId: [],
    franchiseId: []
  });

  constructor(
    protected companyCarrierAccountService: CompanyCarrierAccountService,
    protected countryService: CountryService,
    protected carrierService: CarrierService,
    protected franchiseService: FranchiseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ companyCarrierAccount }) => {
      this.updateForm(companyCarrierAccount);

      this.countryService.query().subscribe((res: HttpResponse<ICountry[]>) => (this.countries = res.body || []));

      this.carrierService.query().subscribe((res: HttpResponse<ICarrier[]>) => (this.carriers = res.body || []));

      this.franchiseService.query().subscribe((res: HttpResponse<IFranchise[]>) => (this.franchises = res.body || []));
    });
  }

  updateForm(companyCarrierAccount: ICompanyCarrierAccount): void {
    this.editForm.patchValue({
      id: companyCarrierAccount.id,
      accountOwner: companyCarrierAccount.accountOwner,
      accountNumber: companyCarrierAccount.accountNumber,
      meterNumber: companyCarrierAccount.meterNumber,
      key: companyCarrierAccount.key,
      password: companyCarrierAccount.password,
      regionId: companyCarrierAccount.regionId,
      carrierId: companyCarrierAccount.carrierId,
      franchiseId: companyCarrierAccount.franchiseId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const companyCarrierAccount = this.createFromForm();
    if (companyCarrierAccount.id !== undefined) {
      this.subscribeToSaveResponse(this.companyCarrierAccountService.update(companyCarrierAccount));
    } else {
      this.subscribeToSaveResponse(this.companyCarrierAccountService.create(companyCarrierAccount));
    }
  }

  private createFromForm(): ICompanyCarrierAccount {
    return {
      ...new CompanyCarrierAccount(),
      id: this.editForm.get(['id'])!.value,
      accountOwner: this.editForm.get(['accountOwner'])!.value,
      accountNumber: this.editForm.get(['accountNumber'])!.value,
      meterNumber: this.editForm.get(['meterNumber'])!.value,
      key: this.editForm.get(['key'])!.value,
      password: this.editForm.get(['password'])!.value,
      regionId: this.editForm.get(['regionId'])!.value,
      carrierId: this.editForm.get(['carrierId'])!.value,
      franchiseId: this.editForm.get(['franchiseId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompanyCarrierAccount>>): void {
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
