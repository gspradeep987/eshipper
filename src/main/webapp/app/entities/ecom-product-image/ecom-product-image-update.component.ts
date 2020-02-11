import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomProductImage, EcomProductImage } from 'app/shared/model/ecom-product-image.model';
import { EcomProductImageService } from './ecom-product-image.service';
import { IEcomProduct } from 'app/shared/model/ecom-product.model';
import { EcomProductService } from 'app/entities/ecom-product/ecom-product.service';

@Component({
  selector: 'jhi-ecom-product-image-update',
  templateUrl: './ecom-product-image-update.component.html'
})
export class EcomProductImageUpdateComponent implements OnInit {
  isSaving = false;
  ecomproducts: IEcomProduct[] = [];

  editForm = this.fb.group({
    id: [],
    imageName: [null, [Validators.maxLength(100)]],
    imagePath: [null, [Validators.maxLength(100)]],
    ecomProductId: []
  });

  constructor(
    protected ecomProductImageService: EcomProductImageService,
    protected ecomProductService: EcomProductService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomProductImage }) => {
      this.updateForm(ecomProductImage);

      this.ecomProductService.query().subscribe((res: HttpResponse<IEcomProduct[]>) => (this.ecomproducts = res.body || []));
    });
  }

  updateForm(ecomProductImage: IEcomProductImage): void {
    this.editForm.patchValue({
      id: ecomProductImage.id,
      imageName: ecomProductImage.imageName,
      imagePath: ecomProductImage.imagePath,
      ecomProductId: ecomProductImage.ecomProductId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomProductImage = this.createFromForm();
    if (ecomProductImage.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomProductImageService.update(ecomProductImage));
    } else {
      this.subscribeToSaveResponse(this.ecomProductImageService.create(ecomProductImage));
    }
  }

  private createFromForm(): IEcomProductImage {
    return {
      ...new EcomProductImage(),
      id: this.editForm.get(['id'])!.value,
      imageName: this.editForm.get(['imageName'])!.value,
      imagePath: this.editForm.get(['imagePath'])!.value,
      ecomProductId: this.editForm.get(['ecomProductId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomProductImage>>): void {
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

  trackById(index: number, item: IEcomProduct): any {
    return item.id;
  }
}
