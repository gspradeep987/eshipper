import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IEcomStoreMarkup, EcomStoreMarkup } from '../ecom-store-markup.model';
import { EcomStoreMarkupService } from '../service/ecom-store-markup.service';
import { IEcomMarkupPrimary } from 'app/entities/ecom-markup-primary/ecom-markup-primary.model';
import { EcomMarkupPrimaryService } from 'app/entities/ecom-markup-primary/service/ecom-markup-primary.service';
import { IEcomMarkupSecondary } from 'app/entities/ecom-markup-secondary/ecom-markup-secondary.model';
import { EcomMarkupSecondaryService } from 'app/entities/ecom-markup-secondary/service/ecom-markup-secondary.service';
import { IEcomMarkupTertiary } from 'app/entities/ecom-markup-tertiary/ecom-markup-tertiary.model';
import { EcomMarkupTertiaryService } from 'app/entities/ecom-markup-tertiary/service/ecom-markup-tertiary.service';
import { IEcomMarkupQuaternary } from 'app/entities/ecom-markup-quaternary/ecom-markup-quaternary.model';
import { EcomMarkupQuaternaryService } from 'app/entities/ecom-markup-quaternary/service/ecom-markup-quaternary.service';

@Component({
  selector: 'jhi-ecom-store-markup-update',
  templateUrl: './ecom-store-markup-update.component.html',
})
export class EcomStoreMarkupUpdateComponent implements OnInit {
  isSaving = false;

  ecomMarkupPrimariesCollection: IEcomMarkupPrimary[] = [];
  ecomMarkupSecondariesCollection: IEcomMarkupSecondary[] = [];
  ecomMarkupTertiariesCollection: IEcomMarkupTertiary[] = [];
  ecomMarkupQuaternariesCollection: IEcomMarkupQuaternary[] = [];

  editForm = this.fb.group({
    id: [],
    markupType: [null, [Validators.maxLength(20)]],
    minValue: [],
    domesticValue: [],
    intlValue: [],
    flatRateValue: [],
    opexValue: [],
    ecomMarkupPrimary: [],
    ecomMarkupSecondary: [],
    ecomMarkupTertiary: [],
    ecomMarkupQuaternary: [],
  });

  constructor(
    protected ecomStoreMarkupService: EcomStoreMarkupService,
    protected ecomMarkupPrimaryService: EcomMarkupPrimaryService,
    protected ecomMarkupSecondaryService: EcomMarkupSecondaryService,
    protected ecomMarkupTertiaryService: EcomMarkupTertiaryService,
    protected ecomMarkupQuaternaryService: EcomMarkupQuaternaryService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStoreMarkup }) => {
      this.updateForm(ecomStoreMarkup);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomStoreMarkup = this.createFromForm();
    if (ecomStoreMarkup.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomStoreMarkupService.update(ecomStoreMarkup));
    } else {
      this.subscribeToSaveResponse(this.ecomStoreMarkupService.create(ecomStoreMarkup));
    }
  }

  trackEcomMarkupPrimaryById(index: number, item: IEcomMarkupPrimary): number {
    return item.id!;
  }

  trackEcomMarkupSecondaryById(index: number, item: IEcomMarkupSecondary): number {
    return item.id!;
  }

  trackEcomMarkupTertiaryById(index: number, item: IEcomMarkupTertiary): number {
    return item.id!;
  }

  trackEcomMarkupQuaternaryById(index: number, item: IEcomMarkupQuaternary): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomStoreMarkup>>): void {
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

  protected updateForm(ecomStoreMarkup: IEcomStoreMarkup): void {
    this.editForm.patchValue({
      id: ecomStoreMarkup.id,
      markupType: ecomStoreMarkup.markupType,
      minValue: ecomStoreMarkup.minValue,
      domesticValue: ecomStoreMarkup.domesticValue,
      intlValue: ecomStoreMarkup.intlValue,
      flatRateValue: ecomStoreMarkup.flatRateValue,
      opexValue: ecomStoreMarkup.opexValue,
      ecomMarkupPrimary: ecomStoreMarkup.ecomMarkupPrimary,
      ecomMarkupSecondary: ecomStoreMarkup.ecomMarkupSecondary,
      ecomMarkupTertiary: ecomStoreMarkup.ecomMarkupTertiary,
      ecomMarkupQuaternary: ecomStoreMarkup.ecomMarkupQuaternary,
    });

    this.ecomMarkupPrimariesCollection = this.ecomMarkupPrimaryService.addEcomMarkupPrimaryToCollectionIfMissing(
      this.ecomMarkupPrimariesCollection,
      ecomStoreMarkup.ecomMarkupPrimary
    );
    this.ecomMarkupSecondariesCollection = this.ecomMarkupSecondaryService.addEcomMarkupSecondaryToCollectionIfMissing(
      this.ecomMarkupSecondariesCollection,
      ecomStoreMarkup.ecomMarkupSecondary
    );
    this.ecomMarkupTertiariesCollection = this.ecomMarkupTertiaryService.addEcomMarkupTertiaryToCollectionIfMissing(
      this.ecomMarkupTertiariesCollection,
      ecomStoreMarkup.ecomMarkupTertiary
    );
    this.ecomMarkupQuaternariesCollection = this.ecomMarkupQuaternaryService.addEcomMarkupQuaternaryToCollectionIfMissing(
      this.ecomMarkupQuaternariesCollection,
      ecomStoreMarkup.ecomMarkupQuaternary
    );
  }

  protected loadRelationshipsOptions(): void {
    this.ecomMarkupPrimaryService
      .query({ filter: 'ecomstoremarkup-is-null' })
      .pipe(map((res: HttpResponse<IEcomMarkupPrimary[]>) => res.body ?? []))
      .pipe(
        map((ecomMarkupPrimaries: IEcomMarkupPrimary[]) =>
          this.ecomMarkupPrimaryService.addEcomMarkupPrimaryToCollectionIfMissing(
            ecomMarkupPrimaries,
            this.editForm.get('ecomMarkupPrimary')!.value
          )
        )
      )
      .subscribe((ecomMarkupPrimaries: IEcomMarkupPrimary[]) => (this.ecomMarkupPrimariesCollection = ecomMarkupPrimaries));

    this.ecomMarkupSecondaryService
      .query({ filter: 'ecomstoremarkup-is-null' })
      .pipe(map((res: HttpResponse<IEcomMarkupSecondary[]>) => res.body ?? []))
      .pipe(
        map((ecomMarkupSecondaries: IEcomMarkupSecondary[]) =>
          this.ecomMarkupSecondaryService.addEcomMarkupSecondaryToCollectionIfMissing(
            ecomMarkupSecondaries,
            this.editForm.get('ecomMarkupSecondary')!.value
          )
        )
      )
      .subscribe((ecomMarkupSecondaries: IEcomMarkupSecondary[]) => (this.ecomMarkupSecondariesCollection = ecomMarkupSecondaries));

    this.ecomMarkupTertiaryService
      .query({ filter: 'ecomstoremarkup-is-null' })
      .pipe(map((res: HttpResponse<IEcomMarkupTertiary[]>) => res.body ?? []))
      .pipe(
        map((ecomMarkupTertiaries: IEcomMarkupTertiary[]) =>
          this.ecomMarkupTertiaryService.addEcomMarkupTertiaryToCollectionIfMissing(
            ecomMarkupTertiaries,
            this.editForm.get('ecomMarkupTertiary')!.value
          )
        )
      )
      .subscribe((ecomMarkupTertiaries: IEcomMarkupTertiary[]) => (this.ecomMarkupTertiariesCollection = ecomMarkupTertiaries));

    this.ecomMarkupQuaternaryService
      .query({ filter: 'ecomstoremarkup-is-null' })
      .pipe(map((res: HttpResponse<IEcomMarkupQuaternary[]>) => res.body ?? []))
      .pipe(
        map((ecomMarkupQuaternaries: IEcomMarkupQuaternary[]) =>
          this.ecomMarkupQuaternaryService.addEcomMarkupQuaternaryToCollectionIfMissing(
            ecomMarkupQuaternaries,
            this.editForm.get('ecomMarkupQuaternary')!.value
          )
        )
      )
      .subscribe((ecomMarkupQuaternaries: IEcomMarkupQuaternary[]) => (this.ecomMarkupQuaternariesCollection = ecomMarkupQuaternaries));
  }

  protected createFromForm(): IEcomStoreMarkup {
    return {
      ...new EcomStoreMarkup(),
      id: this.editForm.get(['id'])!.value,
      markupType: this.editForm.get(['markupType'])!.value,
      minValue: this.editForm.get(['minValue'])!.value,
      domesticValue: this.editForm.get(['domesticValue'])!.value,
      intlValue: this.editForm.get(['intlValue'])!.value,
      flatRateValue: this.editForm.get(['flatRateValue'])!.value,
      opexValue: this.editForm.get(['opexValue'])!.value,
      ecomMarkupPrimary: this.editForm.get(['ecomMarkupPrimary'])!.value,
      ecomMarkupSecondary: this.editForm.get(['ecomMarkupSecondary'])!.value,
      ecomMarkupTertiary: this.editForm.get(['ecomMarkupTertiary'])!.value,
      ecomMarkupQuaternary: this.editForm.get(['ecomMarkupQuaternary'])!.value,
    };
  }
}
