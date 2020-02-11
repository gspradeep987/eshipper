import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IEcomStoreMarkup, EcomStoreMarkup } from 'app/shared/model/ecom-store-markup.model';
import { EcomStoreMarkupService } from './ecom-store-markup.service';
import { IEcomMarkupPrimary } from 'app/shared/model/ecom-markup-primary.model';
import { EcomMarkupPrimaryService } from 'app/entities/ecom-markup-primary/ecom-markup-primary.service';
import { IEcomMarkupSecondary } from 'app/shared/model/ecom-markup-secondary.model';
import { EcomMarkupSecondaryService } from 'app/entities/ecom-markup-secondary/ecom-markup-secondary.service';
import { IEcomMarkupTertiary } from 'app/shared/model/ecom-markup-tertiary.model';
import { EcomMarkupTertiaryService } from 'app/entities/ecom-markup-tertiary/ecom-markup-tertiary.service';
import { IEcomMarkupQuaternary } from 'app/shared/model/ecom-markup-quaternary.model';
import { EcomMarkupQuaternaryService } from 'app/entities/ecom-markup-quaternary/ecom-markup-quaternary.service';

type SelectableEntity = IEcomMarkupPrimary | IEcomMarkupSecondary | IEcomMarkupTertiary | IEcomMarkupQuaternary;

@Component({
  selector: 'jhi-ecom-store-markup-update',
  templateUrl: './ecom-store-markup-update.component.html'
})
export class EcomStoreMarkupUpdateComponent implements OnInit {
  isSaving = false;
  ecommarkupprimaries: IEcomMarkupPrimary[] = [];
  ecommarkupsecondaries: IEcomMarkupSecondary[] = [];
  ecommarkuptertiaries: IEcomMarkupTertiary[] = [];
  ecommarkupquaternaries: IEcomMarkupQuaternary[] = [];

  editForm = this.fb.group({
    id: [],
    markupType: [null, [Validators.maxLength(20)]],
    minValue: [],
    domesticValue: [],
    intlValue: [],
    flatRateValue: [],
    opexValue: [],
    ecomMarkupPrimaryId: [],
    ecomMarkupSecondaryId: [],
    ecomMarkupTertiaryId: [],
    ecomMarkupQuaternaryId: []
  });

  constructor(
    protected ecomStoreMarkupService: EcomStoreMarkupService,
    protected ecomMarkupPrimaryService: EcomMarkupPrimaryService,
    protected ecomMarkupSecondaryService: EcomMarkupSecondaryService,
    protected ecomMarkupTertiaryService: EcomMarkupTertiaryService,
    protected ecomMarkupQuaternaryService: EcomMarkupQuaternaryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStoreMarkup }) => {
      this.updateForm(ecomStoreMarkup);

      this.ecomMarkupPrimaryService
        .query({ filter: 'ecomstoremarkup-is-null' })
        .pipe(
          map((res: HttpResponse<IEcomMarkupPrimary[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IEcomMarkupPrimary[]) => {
          if (!ecomStoreMarkup.ecomMarkupPrimaryId) {
            this.ecommarkupprimaries = resBody;
          } else {
            this.ecomMarkupPrimaryService
              .find(ecomStoreMarkup.ecomMarkupPrimaryId)
              .pipe(
                map((subRes: HttpResponse<IEcomMarkupPrimary>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IEcomMarkupPrimary[]) => (this.ecommarkupprimaries = concatRes));
          }
        });

      this.ecomMarkupSecondaryService
        .query({ filter: 'ecomstoremarkup-is-null' })
        .pipe(
          map((res: HttpResponse<IEcomMarkupSecondary[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IEcomMarkupSecondary[]) => {
          if (!ecomStoreMarkup.ecomMarkupSecondaryId) {
            this.ecommarkupsecondaries = resBody;
          } else {
            this.ecomMarkupSecondaryService
              .find(ecomStoreMarkup.ecomMarkupSecondaryId)
              .pipe(
                map((subRes: HttpResponse<IEcomMarkupSecondary>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IEcomMarkupSecondary[]) => (this.ecommarkupsecondaries = concatRes));
          }
        });

      this.ecomMarkupTertiaryService
        .query({ filter: 'ecomstoremarkup-is-null' })
        .pipe(
          map((res: HttpResponse<IEcomMarkupTertiary[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IEcomMarkupTertiary[]) => {
          if (!ecomStoreMarkup.ecomMarkupTertiaryId) {
            this.ecommarkuptertiaries = resBody;
          } else {
            this.ecomMarkupTertiaryService
              .find(ecomStoreMarkup.ecomMarkupTertiaryId)
              .pipe(
                map((subRes: HttpResponse<IEcomMarkupTertiary>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IEcomMarkupTertiary[]) => (this.ecommarkuptertiaries = concatRes));
          }
        });

      this.ecomMarkupQuaternaryService
        .query({ filter: 'ecomstoremarkup-is-null' })
        .pipe(
          map((res: HttpResponse<IEcomMarkupQuaternary[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IEcomMarkupQuaternary[]) => {
          if (!ecomStoreMarkup.ecomMarkupQuaternaryId) {
            this.ecommarkupquaternaries = resBody;
          } else {
            this.ecomMarkupQuaternaryService
              .find(ecomStoreMarkup.ecomMarkupQuaternaryId)
              .pipe(
                map((subRes: HttpResponse<IEcomMarkupQuaternary>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IEcomMarkupQuaternary[]) => (this.ecommarkupquaternaries = concatRes));
          }
        });
    });
  }

  updateForm(ecomStoreMarkup: IEcomStoreMarkup): void {
    this.editForm.patchValue({
      id: ecomStoreMarkup.id,
      markupType: ecomStoreMarkup.markupType,
      minValue: ecomStoreMarkup.minValue,
      domesticValue: ecomStoreMarkup.domesticValue,
      intlValue: ecomStoreMarkup.intlValue,
      flatRateValue: ecomStoreMarkup.flatRateValue,
      opexValue: ecomStoreMarkup.opexValue,
      ecomMarkupPrimaryId: ecomStoreMarkup.ecomMarkupPrimaryId,
      ecomMarkupSecondaryId: ecomStoreMarkup.ecomMarkupSecondaryId,
      ecomMarkupTertiaryId: ecomStoreMarkup.ecomMarkupTertiaryId,
      ecomMarkupQuaternaryId: ecomStoreMarkup.ecomMarkupQuaternaryId
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

  private createFromForm(): IEcomStoreMarkup {
    return {
      ...new EcomStoreMarkup(),
      id: this.editForm.get(['id'])!.value,
      markupType: this.editForm.get(['markupType'])!.value,
      minValue: this.editForm.get(['minValue'])!.value,
      domesticValue: this.editForm.get(['domesticValue'])!.value,
      intlValue: this.editForm.get(['intlValue'])!.value,
      flatRateValue: this.editForm.get(['flatRateValue'])!.value,
      opexValue: this.editForm.get(['opexValue'])!.value,
      ecomMarkupPrimaryId: this.editForm.get(['ecomMarkupPrimaryId'])!.value,
      ecomMarkupSecondaryId: this.editForm.get(['ecomMarkupSecondaryId'])!.value,
      ecomMarkupTertiaryId: this.editForm.get(['ecomMarkupTertiaryId'])!.value,
      ecomMarkupQuaternaryId: this.editForm.get(['ecomMarkupQuaternaryId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomStoreMarkup>>): void {
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
