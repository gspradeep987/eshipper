import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomOrderAttachment, EcomOrderAttachment } from 'app/shared/model/ecom-order-attachment.model';
import { EcomOrderAttachmentService } from './ecom-order-attachment.service';
import { IEcomOrder } from 'app/shared/model/ecom-order.model';
import { EcomOrderService } from 'app/entities/ecom-order/ecom-order.service';

@Component({
  selector: 'jhi-ecom-order-attachment-update',
  templateUrl: './ecom-order-attachment-update.component.html'
})
export class EcomOrderAttachmentUpdateComponent implements OnInit {
  isSaving = false;
  ecomorders: IEcomOrder[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]],
    attachmentPath: [null, [Validators.maxLength(255)]],
    ecomOrderId: []
  });

  constructor(
    protected ecomOrderAttachmentService: EcomOrderAttachmentService,
    protected ecomOrderService: EcomOrderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomOrderAttachment }) => {
      this.updateForm(ecomOrderAttachment);

      this.ecomOrderService.query().subscribe((res: HttpResponse<IEcomOrder[]>) => (this.ecomorders = res.body || []));
    });
  }

  updateForm(ecomOrderAttachment: IEcomOrderAttachment): void {
    this.editForm.patchValue({
      id: ecomOrderAttachment.id,
      name: ecomOrderAttachment.name,
      attachmentPath: ecomOrderAttachment.attachmentPath,
      ecomOrderId: ecomOrderAttachment.ecomOrderId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomOrderAttachment = this.createFromForm();
    if (ecomOrderAttachment.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomOrderAttachmentService.update(ecomOrderAttachment));
    } else {
      this.subscribeToSaveResponse(this.ecomOrderAttachmentService.create(ecomOrderAttachment));
    }
  }

  private createFromForm(): IEcomOrderAttachment {
    return {
      ...new EcomOrderAttachment(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      attachmentPath: this.editForm.get(['attachmentPath'])!.value,
      ecomOrderId: this.editForm.get(['ecomOrderId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomOrderAttachment>>): void {
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

  trackById(index: number, item: IEcomOrder): any {
    return item.id;
  }
}
