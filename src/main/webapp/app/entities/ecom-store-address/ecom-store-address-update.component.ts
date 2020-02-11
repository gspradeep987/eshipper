import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomStoreAddress, EcomStoreAddress } from 'app/shared/model/ecom-store-address.model';
import { EcomStoreAddressService } from './ecom-store-address.service';
import { ICountry } from 'app/shared/model/country.model';
import { CountryService } from 'app/entities/country/country.service';
import { IProvince } from 'app/shared/model/province.model';
import { ProvinceService } from 'app/entities/province/province.service';
import { ICity } from 'app/shared/model/city.model';
import { CityService } from 'app/entities/city/city.service';

type SelectableEntity = ICountry | IProvince | ICity;

@Component({
  selector: 'jhi-ecom-store-address-update',
  templateUrl: './ecom-store-address-update.component.html'
})
export class EcomStoreAddressUpdateComponent implements OnInit {
  isSaving = false;
  countries: ICountry[] = [];
  provinces: IProvince[] = [];
  cities: ICity[] = [];

  editForm = this.fb.group({
    id: [],
    address1: [null, [Validators.maxLength(255)]],
    address2: [null, [Validators.maxLength(255)]],
    name: [null, [Validators.maxLength(255)]],
    phone: [null, [Validators.maxLength(25)]],
    emailAccId: [null, [Validators.maxLength(255)]],
    defaultAddress: [],
    countryId: [],
    provinceId: [],
    cityId: []
  });

  constructor(
    protected ecomStoreAddressService: EcomStoreAddressService,
    protected countryService: CountryService,
    protected provinceService: ProvinceService,
    protected cityService: CityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStoreAddress }) => {
      this.updateForm(ecomStoreAddress);

      this.countryService.query().subscribe((res: HttpResponse<ICountry[]>) => (this.countries = res.body || []));

      this.provinceService.query().subscribe((res: HttpResponse<IProvince[]>) => (this.provinces = res.body || []));

      this.cityService.query().subscribe((res: HttpResponse<ICity[]>) => (this.cities = res.body || []));
    });
  }

  updateForm(ecomStoreAddress: IEcomStoreAddress): void {
    this.editForm.patchValue({
      id: ecomStoreAddress.id,
      address1: ecomStoreAddress.address1,
      address2: ecomStoreAddress.address2,
      name: ecomStoreAddress.name,
      phone: ecomStoreAddress.phone,
      emailAccId: ecomStoreAddress.emailAccId,
      defaultAddress: ecomStoreAddress.defaultAddress,
      countryId: ecomStoreAddress.countryId,
      provinceId: ecomStoreAddress.provinceId,
      cityId: ecomStoreAddress.cityId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomStoreAddress = this.createFromForm();
    if (ecomStoreAddress.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomStoreAddressService.update(ecomStoreAddress));
    } else {
      this.subscribeToSaveResponse(this.ecomStoreAddressService.create(ecomStoreAddress));
    }
  }

  private createFromForm(): IEcomStoreAddress {
    return {
      ...new EcomStoreAddress(),
      id: this.editForm.get(['id'])!.value,
      address1: this.editForm.get(['address1'])!.value,
      address2: this.editForm.get(['address2'])!.value,
      name: this.editForm.get(['name'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      emailAccId: this.editForm.get(['emailAccId'])!.value,
      defaultAddress: this.editForm.get(['defaultAddress'])!.value,
      countryId: this.editForm.get(['countryId'])!.value,
      provinceId: this.editForm.get(['provinceId'])!.value,
      cityId: this.editForm.get(['cityId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomStoreAddress>>): void {
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
