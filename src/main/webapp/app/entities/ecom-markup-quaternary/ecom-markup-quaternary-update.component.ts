import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomMarkupQuaternary, EcomMarkupQuaternary } from 'app/shared/model/ecom-markup-quaternary.model';
import { EcomMarkupQuaternaryService } from './ecom-markup-quaternary.service';
import { ICountry } from 'app/shared/model/country.model';
import { CountryService } from 'app/entities/country/country.service';

@Component({
  selector: 'jhi-ecom-markup-quaternary-update',
  templateUrl: './ecom-markup-quaternary-update.component.html'
})
export class EcomMarkupQuaternaryUpdateComponent implements OnInit {
  isSaving = false;
  countries: ICountry[] = [];

  editForm = this.fb.group({
    id: [],
    value: [],
    countryId: []
  });

  constructor(
    protected ecomMarkupQuaternaryService: EcomMarkupQuaternaryService,
    protected countryService: CountryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomMarkupQuaternary }) => {
      this.updateForm(ecomMarkupQuaternary);

      this.countryService.query().subscribe((res: HttpResponse<ICountry[]>) => (this.countries = res.body || []));
    });
  }

  updateForm(ecomMarkupQuaternary: IEcomMarkupQuaternary): void {
    this.editForm.patchValue({
      id: ecomMarkupQuaternary.id,
      value: ecomMarkupQuaternary.value,
      countryId: ecomMarkupQuaternary.countryId
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

  private createFromForm(): IEcomMarkupQuaternary {
    return {
      ...new EcomMarkupQuaternary(),
      id: this.editForm.get(['id'])!.value,
      value: this.editForm.get(['value'])!.value,
      countryId: this.editForm.get(['countryId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomMarkupQuaternary>>): void {
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

  trackById(index: number, item: ICountry): any {
    return item.id;
  }
}
