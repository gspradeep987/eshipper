import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IEcomMailTemplate, EcomMailTemplate } from '../ecom-mail-template.model';
import { EcomMailTemplateService } from '../service/ecom-mail-template.service';
import { IEcomStore } from 'app/entities/ecom-store/ecom-store.model';
import { EcomStoreService } from 'app/entities/ecom-store/service/ecom-store.service';

@Component({
  selector: 'jhi-ecom-mail-template-update',
  templateUrl: './ecom-mail-template-update.component.html',
})
export class EcomMailTemplateUpdateComponent implements OnInit {
  isSaving = false;

  ecomStoresSharedCollection: IEcomStore[] = [];

  editForm = this.fb.group({
    id: [],
    templateName: [null, [Validators.maxLength(50)]],
    templateType: [null, [Validators.maxLength(50)]],
    note: [null, [Validators.maxLength(50)]],
    subject: [null, [Validators.maxLength(50)]],
    content: [null, [Validators.maxLength(50)]],
    isCustomTemplate: [],
    isOrder: [],
    isShipment: [],
    isProductPurchased: [],
    isAmountPaid: [],
    isStoreInfo: [],
    isBody1: [],
    ecomStore: [],
  });

  constructor(
    protected ecomMailTemplateService: EcomMailTemplateService,
    protected ecomStoreService: EcomStoreService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomMailTemplate }) => {
      this.updateForm(ecomMailTemplate);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomMailTemplate = this.createFromForm();
    if (ecomMailTemplate.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomMailTemplateService.update(ecomMailTemplate));
    } else {
      this.subscribeToSaveResponse(this.ecomMailTemplateService.create(ecomMailTemplate));
    }
  }

  trackEcomStoreById(index: number, item: IEcomStore): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomMailTemplate>>): void {
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

  protected updateForm(ecomMailTemplate: IEcomMailTemplate): void {
    this.editForm.patchValue({
      id: ecomMailTemplate.id,
      templateName: ecomMailTemplate.templateName,
      templateType: ecomMailTemplate.templateType,
      note: ecomMailTemplate.note,
      subject: ecomMailTemplate.subject,
      content: ecomMailTemplate.content,
      isCustomTemplate: ecomMailTemplate.isCustomTemplate,
      isOrder: ecomMailTemplate.isOrder,
      isShipment: ecomMailTemplate.isShipment,
      isProductPurchased: ecomMailTemplate.isProductPurchased,
      isAmountPaid: ecomMailTemplate.isAmountPaid,
      isStoreInfo: ecomMailTemplate.isStoreInfo,
      isBody1: ecomMailTemplate.isBody1,
      ecomStore: ecomMailTemplate.ecomStore,
    });

    this.ecomStoresSharedCollection = this.ecomStoreService.addEcomStoreToCollectionIfMissing(
      this.ecomStoresSharedCollection,
      ecomMailTemplate.ecomStore
    );
  }

  protected loadRelationshipsOptions(): void {
    this.ecomStoreService
      .query()
      .pipe(map((res: HttpResponse<IEcomStore[]>) => res.body ?? []))
      .pipe(
        map((ecomStores: IEcomStore[]) =>
          this.ecomStoreService.addEcomStoreToCollectionIfMissing(ecomStores, this.editForm.get('ecomStore')!.value)
        )
      )
      .subscribe((ecomStores: IEcomStore[]) => (this.ecomStoresSharedCollection = ecomStores));
  }

  protected createFromForm(): IEcomMailTemplate {
    return {
      ...new EcomMailTemplate(),
      id: this.editForm.get(['id'])!.value,
      templateName: this.editForm.get(['templateName'])!.value,
      templateType: this.editForm.get(['templateType'])!.value,
      note: this.editForm.get(['note'])!.value,
      subject: this.editForm.get(['subject'])!.value,
      content: this.editForm.get(['content'])!.value,
      isCustomTemplate: this.editForm.get(['isCustomTemplate'])!.value,
      isOrder: this.editForm.get(['isOrder'])!.value,
      isShipment: this.editForm.get(['isShipment'])!.value,
      isProductPurchased: this.editForm.get(['isProductPurchased'])!.value,
      isAmountPaid: this.editForm.get(['isAmountPaid'])!.value,
      isStoreInfo: this.editForm.get(['isStoreInfo'])!.value,
      isBody1: this.editForm.get(['isBody1'])!.value,
      ecomStore: this.editForm.get(['ecomStore'])!.value,
    };
  }
}
