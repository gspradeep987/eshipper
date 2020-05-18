import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomAutomationRules, EcomAutomationRules } from 'app/shared/model/ecom-automation-rules.model';
import { EcomAutomationRulesService } from './ecom-automation-rules.service';
import { IEcomStore } from 'app/shared/model/ecom-store.model';
import { EcomStoreService } from 'app/entities/ecom-store/ecom-store.service';

@Component({
  selector: 'jhi-ecom-automation-rules-update',
  templateUrl: './ecom-automation-rules-update.component.html',
})
export class EcomAutomationRulesUpdateComponent implements OnInit {
  isSaving = false;
  ecomstores: IEcomStore[] = [];
  createdDateDp: any;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]],
    enable: [],
    createdDate: [],
    createdBy: [null, [Validators.maxLength(255)]],
    ecomStoreId: [],
  });

  constructor(
    protected ecomAutomationRulesService: EcomAutomationRulesService,
    protected ecomStoreService: EcomStoreService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomAutomationRules }) => {
      this.updateForm(ecomAutomationRules);

      this.ecomStoreService.query().subscribe((res: HttpResponse<IEcomStore[]>) => (this.ecomstores = res.body || []));
    });
  }

  updateForm(ecomAutomationRules: IEcomAutomationRules): void {
    this.editForm.patchValue({
      id: ecomAutomationRules.id,
      name: ecomAutomationRules.name,
      enable: ecomAutomationRules.enable,
      createdDate: ecomAutomationRules.createdDate,
      createdBy: ecomAutomationRules.createdBy,
      ecomStoreId: ecomAutomationRules.ecomStoreId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomAutomationRules = this.createFromForm();
    if (ecomAutomationRules.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomAutomationRulesService.update(ecomAutomationRules));
    } else {
      this.subscribeToSaveResponse(this.ecomAutomationRulesService.create(ecomAutomationRules));
    }
  }

  private createFromForm(): IEcomAutomationRules {
    return {
      ...new EcomAutomationRules(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      enable: this.editForm.get(['enable'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      ecomStoreId: this.editForm.get(['ecomStoreId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomAutomationRules>>): void {
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
