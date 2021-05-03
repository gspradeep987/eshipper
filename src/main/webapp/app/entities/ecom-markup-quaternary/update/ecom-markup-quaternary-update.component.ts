import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IEcomMarkupQuaternary, EcomMarkupQuaternary } from '../ecom-markup-quaternary.model';
import { EcomMarkupQuaternaryService } from '../service/ecom-markup-quaternary.service';
import { ICountry } from 'app/entities/country/country.model';
import { CountryService } from 'app/entities/country/service/country.service';

@Component({
  selector: 'jhi-ecom-markup-quaternary-update',
  templateUrl: './ecom-markup-quaternary-update.component.html',
})
export class EcomMarkupQuaternaryUpdateComponent implements OnInit {
  isSaving = false;

  countriesSharedCollection: ICountry[] = [];

  editForm = this.fb.group({
    id: [],
    value: [],
    country: [],
  });

  constructor(
    protected ecomMarkupQuaternaryService: EcomMarkupQuaternaryService,
    protected countryService: CountryService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomMarkupQuaternary }) => {
      this.updateForm(ecomMarkupQuaternary);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomMarkupQuaternary = this.createFromForm();
    if (ecomMarkupQuaternary.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomMarkupQuaternaryService.update(ecomMarkupQuaternary));
    } else {
      this.subscribeToSaveResponse(this.ecomMarkupQuaternaryService.create(ecomMarkupQuaternary));
    }
  }

  trackCountryById(index: number, item: ICountry): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomMarkupQuaternary>>): void {
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

  protected updateForm(ecomMarkupQuaternary: IEcomMarkupQuaternary): void {
    this.editForm.patchValue({
      id: ecomMarkupQuaternary.id,
      value: ecomMarkupQuaternary.value,
      country: ecomMarkupQuaternary.country,
    });

    this.countriesSharedCollection = this.countryService.addCountryToCollectionIfMissing(
      this.countriesSharedCollection,
      ecomMarkupQuaternary.country
    );
  }

  protected loadRelationshipsOptions(): void {
    this.countryService
      .query()
      .pipe(map((res: HttpResponse<ICountry[]>) => res.body ?? []))
      .pipe(
        map((countries: ICountry[]) => this.countryService.addCountryToCollectionIfMissing(countries, this.editForm.get('country')!.value))
      )
      .subscribe((countries: ICountry[]) => (this.countriesSharedCollection = countries));
  }

  protected createFromForm(): IEcomMarkupQuaternary {
    return {
      ...new EcomMarkupQuaternary(),
      id: this.editForm.get(['id'])!.value,
      value: this.editForm.get(['value'])!.value,
      country: this.editForm.get(['country'])!.value,
    };
  }
}
