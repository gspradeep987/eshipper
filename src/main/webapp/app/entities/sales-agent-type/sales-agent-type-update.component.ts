import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISalesAgentType, SalesAgentType } from 'app/shared/model/sales-agent-type.model';
import { SalesAgentTypeService } from './sales-agent-type.service';

@Component({
  selector: 'jhi-sales-agent-type-update',
  templateUrl: './sales-agent-type-update.component.html',
})
export class SalesAgentTypeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    agentType: [],
  });

  constructor(protected salesAgentTypeService: SalesAgentTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ salesAgentType }) => {
      this.updateForm(salesAgentType);
    });
  }

  updateForm(salesAgentType: ISalesAgentType): void {
    this.editForm.patchValue({
      id: salesAgentType.id,
      agentType: salesAgentType.agentType,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const salesAgentType = this.createFromForm();
    if (salesAgentType.id !== undefined) {
      this.subscribeToSaveResponse(this.salesAgentTypeService.update(salesAgentType));
    } else {
      this.subscribeToSaveResponse(this.salesAgentTypeService.create(salesAgentType));
    }
  }

  private createFromForm(): ISalesAgentType {
    return {
      ...new SalesAgentType(),
      id: this.editForm.get(['id'])!.value,
      agentType: this.editForm.get(['agentType'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISalesAgentType>>): void {
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
}
