import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IEcomStoreAddress, EcomStoreAddress } from '../ecom-store-address.model';
import { EcomStoreAddressService } from '../service/ecom-store-address.service';
import { ICountry } from 'app/entities/country/country.model';
import { CountryService } from 'app/entities/country/service/country.service';
import { IProvince } from 'app/entities/province/province.model';
import { ProvinceService } from 'app/entities/province/service/province.service';
import { ICity } from 'app/entities/city/city.model';
import { CityService } from 'app/entities/city/service/city.service';

@Component({
  selector: 'jhi-ecom-store-address-update',
  templateUrl: './ecom-store-address-update.component.html',
})
export class EcomStoreAddressUpdateComponent implements OnInit {
  isSaving = false;

  countriesSharedCollection: ICountry[] = [];
  provincesSharedCollection: IProvince[] = [];
  citiesSharedCollection: ICity[] = [];

  editForm = this.fb.group({
    id: [],
    address1: [null, [Validators.maxLength(255)]],
    address2: [null, [Validators.maxLength(255)]],
    name: [null, [Validators.maxLength(255)]],
    phone: [null, [Validators.maxLength(25)]],
    emailAccId: [null, [Validators.maxLength(255)]],
    defaultAddress: [],
    country: [],
    province: [],
    city: [],
  });

  constructor(
    protected ecomStoreAddressService: EcomStoreAddressService,
    protected countryService: CountryService,
    protected provinceService: ProvinceService,
    protected cityService: CityService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStoreAddress }) => {
      this.updateForm(ecomStoreAddress);

      this.loadRelationshipsOptions();
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

  trackCountryById(index: number, item: ICountry): number {
    return item.id!;
  }

  trackProvinceById(index: number, item: IProvince): number {
    return item.id!;
  }

  trackCityById(index: number, item: ICity): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomStoreAddress>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(ecomStoreAddress: IEcomStoreAddress): void {
    this.editForm.patchValue({
      id: ecomStoreAddress.id,
      address1: ecomStoreAddress.address1,
      address2: ecomStoreAddress.address2,
      name: ecomStoreAddress.name,
      phone: ecomStoreAddress.phone,
      emailAccId: ecomStoreAddress.emailAccId,
      defaultAddress: ecomStoreAddress.defaultAddress,
      country: ecomStoreAddress.country,
      province: ecomStoreAddress.province,
      city: ecomStoreAddress.city,
    });

    this.countriesSharedCollection = this.countryService.addCountryToCollectionIfMissing(
      this.countriesSharedCollection,
      ecomStoreAddress.country
    );
    this.provincesSharedCollection = this.provinceService.addProvinceToCollectionIfMissing(
      this.provincesSharedCollection,
      ecomStoreAddress.province
    );
    this.citiesSharedCollection = this.cityService.addCityToCollectionIfMissing(this.citiesSharedCollection, ecomStoreAddress.city);
  }

  protected loadRelationshipsOptions(): void {
    this.countryService
      .query()
      .pipe(map((res: HttpResponse<ICountry[]>) => res.body ?? []))
      .pipe(
        map((countries: ICountry[]) => this.countryService.addCountryToCollectionIfMissing(countries, this.editForm.get('country')!.value))
      )
      .subscribe((countries: ICountry[]) => (this.countriesSharedCollection = countries));

    this.provinceService
      .query()
      .pipe(map((res: HttpResponse<IProvince[]>) => res.body ?? []))
      .pipe(
        map((provinces: IProvince[]) =>
          this.provinceService.addProvinceToCollectionIfMissing(provinces, this.editForm.get('province')!.value)
        )
      )
      .subscribe((provinces: IProvince[]) => (this.provincesSharedCollection = provinces));

    this.cityService
      .query()
      .pipe(map((res: HttpResponse<ICity[]>) => res.body ?? []))
      .pipe(map((cities: ICity[]) => this.cityService.addCityToCollectionIfMissing(cities, this.editForm.get('city')!.value)))
      .subscribe((cities: ICity[]) => (this.citiesSharedCollection = cities));
  }

  protected createFromForm(): IEcomStoreAddress {
    return {
      ...new EcomStoreAddress(),
      id: this.editForm.get(['id'])!.value,
      address1: this.editForm.get(['address1'])!.value,
      address2: this.editForm.get(['address2'])!.value,
      name: this.editForm.get(['name'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      emailAccId: this.editForm.get(['emailAccId'])!.value,
      defaultAddress: this.editForm.get(['defaultAddress'])!.value,
      country: this.editForm.get(['country'])!.value,
      province: this.editForm.get(['province'])!.value,
      city: this.editForm.get(['city'])!.value,
    };
  }
}
