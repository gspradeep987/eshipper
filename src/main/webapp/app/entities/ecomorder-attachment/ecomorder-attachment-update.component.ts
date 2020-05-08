import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomorderAttachment, EcomorderAttachment } from 'app/shared/model/ecomorder-attachment.model';
import { EcomorderAttachmentService } from './ecomorder-attachment.service';
import { IEcomOrder } from 'app/shared/model/ecom-order.model';
import { EcomOrderService } from 'app/entities/ecom-order/ecom-order.service';

@Component({
  selector: 'jhi-ecomorder-attachment-update',
  templateUrl: './ecomorder-attachment-update.component.html'
})
export class EcomorderAttachmentUpdateComponent implements OnInit {
  isSaving = false;
  ecomorders: IEcomOrder[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]],
    attachmentPath: [null, [Validators.maxLength(255)]],
    ecomOrderId: []
  });

  constructor(
    protected ecomorderAttachmentService: EcomorderAttachmentService,
    protected ecomOrderService: EcomOrderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomorderAttachment }) => {
      this.updateForm(ecomorderAttachment);

      this.ecomOrderService.query().subscribe((res: HttpResponse<IEcomOrder[]>) => (this.ecomorders = res.body || []));
    });
  }

  updateForm(ecomorderAttachment: IEcomorderAttachment): void {
    this.editForm.patchValue({
      id: ecomorderAttachment.id,
      name: ecomorderAttachment.name,
      attachmentPath: ecomorderAttachment.attachmentPath,
      ecomOrderId: ecomorderAttachment.ecomOrderId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomorderAttachment = this.createFromForm();
    if (ecomorderAttachment.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomorderAttachmentService.update(ecomorderAttachment));
    } else {
      this.subscribeToSaveResponse(this.ecomorderAttachmentService.create(ecomorderAttachment));
    }
  }

  private createFromForm(): IEcomorderAttachment {
    return {
      ...new EcomorderAttachment(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      attachmentPath: this.editForm.get(['attachmentPath'])!.value,
      ecomOrderId: this.editForm.get(['ecomOrderId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomorderAttachment>>): void {
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
