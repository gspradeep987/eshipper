import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IEcomProductImage, EcomProductImage } from '../ecom-product-image.model';
import { EcomProductImageService } from '../service/ecom-product-image.service';
import { IEcomProduct } from 'app/entities/ecom-product/ecom-product.model';
import { EcomProductService } from 'app/entities/ecom-product/service/ecom-product.service';

@Component({
  selector: 'jhi-ecom-product-image-update',
  templateUrl: './ecom-product-image-update.component.html',
})
export class EcomProductImageUpdateComponent implements OnInit {
  isSaving = false;

  ecomProductsSharedCollection: IEcomProduct[] = [];

  editForm = this.fb.group({
    id: [],
    imageName: [null, [Validators.maxLength(100)]],
    imagePath: [null, [Validators.maxLength(100)]],
    ecomProduct: [],
  });

  constructor(
    protected ecomProductImageService: EcomProductImageService,
    protected ecomProductService: EcomProductService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomProductImage }) => {
      this.updateForm(ecomProductImage);

      this.loadRelationshipsOptions();
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

  trackEcomProductById(index: number, item: IEcomProduct): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomProductImage>>): void {
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

  protected updateForm(ecomProductImage: IEcomProductImage): void {
    this.editForm.patchValue({
      id: ecomProductImage.id,
      imageName: ecomProductImage.imageName,
      imagePath: ecomProductImage.imagePath,
      ecomProduct: ecomProductImage.ecomProduct,
    });

    this.ecomProductsSharedCollection = this.ecomProductService.addEcomProductToCollectionIfMissing(
      this.ecomProductsSharedCollection,
      ecomProductImage.ecomProduct
    );
  }

  protected loadRelationshipsOptions(): void {
    this.ecomProductService
      .query()
      .pipe(map((res: HttpResponse<IEcomProduct[]>) => res.body ?? []))
      .pipe(
        map((ecomProducts: IEcomProduct[]) =>
          this.ecomProductService.addEcomProductToCollectionIfMissing(ecomProducts, this.editForm.get('ecomProduct')!.value)
        )
      )
      .subscribe((ecomProducts: IEcomProduct[]) => (this.ecomProductsSharedCollection = ecomProducts));
  }

  protected createFromForm(): IEcomProductImage {
    return {
      ...new EcomProductImage(),
      id: this.editForm.get(['id'])!.value,
      imageName: this.editForm.get(['imageName'])!.value,
      imagePath: this.editForm.get(['imagePath'])!.value,
      ecomProduct: this.editForm.get(['ecomProduct'])!.value,
    };
  }
}
