import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomStore, EcomStore } from 'app/shared/model/ecom-store.model';
import { EcomStoreService } from './ecom-store.service';

@Component({
  selector: 'jhi-ecom-store-update',
  templateUrl: './ecom-store-update.component.html',
})
export class EcomStoreUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
  });

  constructor(protected ecomStoreService: EcomStoreService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStore }) => {
      this.updateForm(ecomStore);
    });
  }

  updateForm(ecomStore: IEcomStore): void {
    this.editForm.patchValue({
      id: ecomStore.id,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomStore = this.createFromForm();
    if (ecomStore.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomStoreService.update(ecomStore));
    } else {
      this.subscribeToSaveResponse(this.ecomStoreService.create(ecomStore));
    }
  }

  private createFromForm(): IEcomStore {
    return {
      ...new EcomStore(),
      id: this.editForm.get(['id'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomStore>>): void {
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
