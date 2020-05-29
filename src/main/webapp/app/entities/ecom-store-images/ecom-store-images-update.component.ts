import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomStoreImages, EcomStoreImages } from 'app/shared/model/ecom-store-images.model';
import { EcomStoreImagesService } from './ecom-store-images.service';

@Component({
  selector: 'jhi-ecom-store-images-update',
  templateUrl: './ecom-store-images-update.component.html',
})
export class EcomStoreImagesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]],
    imagePath: [null, [Validators.maxLength(255)]],
  });

  constructor(
    protected ecomStoreImagesService: EcomStoreImagesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStoreImages }) => {
      this.updateForm(ecomStoreImages);
    });
  }

  updateForm(ecomStoreImages: IEcomStoreImages): void {
    this.editForm.patchValue({
      id: ecomStoreImages.id,
      name: ecomStoreImages.name,
      imagePath: ecomStoreImages.imagePath,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomStoreImages = this.createFromForm();
    if (ecomStoreImages.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomStoreImagesService.update(ecomStoreImages));
    } else {
      this.subscribeToSaveResponse(this.ecomStoreImagesService.create(ecomStoreImages));
    }
  }

  private createFromForm(): IEcomStoreImages {
    return {
      ...new EcomStoreImages(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      imagePath: this.editForm.get(['imagePath'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomStoreImages>>): void {
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
