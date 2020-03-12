import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomStoreSync, EcomStoreSync } from 'app/shared/model/ecom-store-sync.model';
import { EcomStoreSyncService } from './ecom-store-sync.service';

@Component({
  selector: 'jhi-ecom-store-sync-update',
  templateUrl: './ecom-store-sync-update.component.html'
})
export class EcomStoreSyncUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]],
    syncFrequency: [],
    syncInterval: [null, [Validators.maxLength(25)]]
  });

  constructor(protected ecomStoreSyncService: EcomStoreSyncService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStoreSync }) => {
      this.updateForm(ecomStoreSync);
    });
  }

  updateForm(ecomStoreSync: IEcomStoreSync): void {
    this.editForm.patchValue({
      id: ecomStoreSync.id,
      name: ecomStoreSync.name,
      syncFrequency: ecomStoreSync.syncFrequency,
      syncInterval: ecomStoreSync.syncInterval
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomStoreSync = this.createFromForm();
    if (ecomStoreSync.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomStoreSyncService.update(ecomStoreSync));
    } else {
      this.subscribeToSaveResponse(this.ecomStoreSyncService.create(ecomStoreSync));
    }
  }

  private createFromForm(): IEcomStoreSync {
    return {
      ...new EcomStoreSync(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      syncFrequency: this.editForm.get(['syncFrequency'])!.value,
      syncInterval: this.editForm.get(['syncInterval'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomStoreSync>>): void {
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
