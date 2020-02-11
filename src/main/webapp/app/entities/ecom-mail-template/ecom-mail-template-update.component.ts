import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomMailTemplate, EcomMailTemplate } from 'app/shared/model/ecom-mail-template.model';
import { EcomMailTemplateService } from './ecom-mail-template.service';
import { IEcomStore } from 'app/shared/model/ecom-store.model';
import { EcomStoreService } from 'app/entities/ecom-store/ecom-store.service';

@Component({
  selector: 'jhi-ecom-mail-template-update',
  templateUrl: './ecom-mail-template-update.component.html'
})
export class EcomMailTemplateUpdateComponent implements OnInit {
  isSaving = false;
  ecomstores: IEcomStore[] = [];

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
    ecomStoreId: []
  });

  constructor(
    protected ecomMailTemplateService: EcomMailTemplateService,
    protected ecomStoreService: EcomStoreService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomMailTemplate }) => {
      this.updateForm(ecomMailTemplate);

      this.ecomStoreService.query().subscribe((res: HttpResponse<IEcomStore[]>) => (this.ecomstores = res.body || []));
    });
  }

  updateForm(ecomMailTemplate: IEcomMailTemplate): void {
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
      ecomStoreId: ecomMailTemplate.ecomStoreId
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

  private createFromForm(): IEcomMailTemplate {
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
      ecomStoreId: this.editForm.get(['ecomStoreId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomMailTemplate>>): void {
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

  trackById(index: number, item: IEcomStore): any {
    return item.id;
  }
}
