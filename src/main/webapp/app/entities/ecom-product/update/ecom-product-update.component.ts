import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IEcomProduct, EcomProduct } from '../ecom-product.model';
import { EcomProductService } from '../service/ecom-product.service';
import { ICountry } from 'app/entities/country/country.model';
import { CountryService } from 'app/entities/country/service/country.service';
import { IEcomWarehouse } from 'app/entities/ecom-warehouse/ecom-warehouse.model';
import { EcomWarehouseService } from 'app/entities/ecom-warehouse/service/ecom-warehouse.service';
import { IEcomOrder } from 'app/entities/ecom-order/ecom-order.model';
import { EcomOrderService } from 'app/entities/ecom-order/service/ecom-order.service';

@Component({
  selector: 'jhi-ecom-product-update',
  templateUrl: './ecom-product-update.component.html',
})
export class EcomProductUpdateComponent implements OnInit {
  isSaving = false;

  countriesSharedCollection: ICountry[] = [];
  ecomWarehousesSharedCollection: IEcomWarehouse[] = [];
  ecomOrdersSharedCollection: IEcomOrder[] = [];

  editForm = this.fb.group({
    id: [],
    title: [null, [Validators.maxLength(100)]],
    length: [],
    width: [],
    height: [],
    weight: [],
    dimUnit: [null, [Validators.maxLength(5)]],
    wghtUnit: [null, [Validators.maxLength(5)]],
    goodsValue: [],
    productValue: [],
    hsCodes: [null, [Validators.maxLength(6)]],
    sku: [],
    policy: [],
    insuranceAmt: [],
    isInsured: [],
    country: [],
    ecomWarehouses: [],
    ecomOrder: [],
  });

  constructor(
    protected ecomProductService: EcomProductService,
    protected countryService: CountryService,
    protected ecomWarehouseService: EcomWarehouseService,
    protected ecomOrderService: EcomOrderService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomProduct }) => {
      this.updateForm(ecomProduct);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomProduct = this.createFromForm();
    if (ecomProduct.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomProductService.update(ecomProduct));
    } else {
      this.subscribeToSaveResponse(this.ecomProductService.create(ecomProduct));
    }
  }

  trackCountryById(index: number, item: ICountry): number {
    return item.id!;
  }

  trackEcomWarehouseById(index: number, item: IEcomWarehouse): number {
    return item.id!;
  }

  trackEcomOrderById(index: number, item: IEcomOrder): number {
    return item.id!;
  }

  getSelectedEcomWarehouse(option: IEcomWarehouse, selectedVals?: IEcomWarehouse[]): IEcomWarehouse {
    if (selectedVals) {
      for (const selectedVal of selectedVals) {
        if (option.id === selectedVal.id) {
          return selectedVal;
        }
      }
    }
    return option;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomProduct>>): void {
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

  protected updateForm(ecomProduct: IEcomProduct): void {
    this.editForm.patchValue({
      id: ecomProduct.id,
      title: ecomProduct.title,
      length: ecomProduct.length,
      width: ecomProduct.width,
      height: ecomProduct.height,
      weight: ecomProduct.weight,
      dimUnit: ecomProduct.dimUnit,
      wghtUnit: ecomProduct.wghtUnit,
      goodsValue: ecomProduct.goodsValue,
      productValue: ecomProduct.productValue,
      hsCodes: ecomProduct.hsCodes,
      sku: ecomProduct.sku,
      policy: ecomProduct.policy,
      insuranceAmt: ecomProduct.insuranceAmt,
      isInsured: ecomProduct.isInsured,
      country: ecomProduct.country,
      ecomWarehouses: ecomProduct.ecomWarehouses,
      ecomOrder: ecomProduct.ecomOrder,
    });

    this.countriesSharedCollection = this.countryService.addCountryToCollectionIfMissing(
      this.countriesSharedCollection,
      ecomProduct.country
    );
    this.ecomWarehousesSharedCollection = this.ecomWarehouseService.addEcomWarehouseToCollectionIfMissing(
      this.ecomWarehousesSharedCollection,
      ...(ecomProduct.ecomWarehouses ?? [])
    );
    this.ecomOrdersSharedCollection = this.ecomOrderService.addEcomOrderToCollectionIfMissing(
      this.ecomOrdersSharedCollection,
      ecomProduct.ecomOrder
    );
  }

  protected loadRelationshipsOptions(): void {
    this.countryService
      .query()
      .pipe(map((res: HttpResponse<ICountry[]>) => res.body ?? []))
      .pipe(
        map((countries: ICountry[]) => this.countryService.addCountryToCollectionIfMissing(countries, this.editForm.get('country')!.value))
      )
      .subscribe((countries: ICountry[]) => (this.countriesSharedCollection = countries));

    this.ecomWarehouseService
      .query()
      .pipe(map((res: HttpResponse<IEcomWarehouse[]>) => res.body ?? []))
      .pipe(
        map((ecomWarehouses: IEcomWarehouse[]) =>
          this.ecomWarehouseService.addEcomWarehouseToCollectionIfMissing(
            ecomWarehouses,
            ...(this.editForm.get('ecomWarehouses')!.value ?? [])
          )
        )
      )
      .subscribe((ecomWarehouses: IEcomWarehouse[]) => (this.ecomWarehousesSharedCollection = ecomWarehouses));

    this.ecomOrderService
      .query()
      .pipe(map((res: HttpResponse<IEcomOrder[]>) => res.body ?? []))
      .pipe(
        map((ecomOrders: IEcomOrder[]) =>
          this.ecomOrderService.addEcomOrderToCollectionIfMissing(ecomOrders, this.editForm.get('ecomOrder')!.value)
        )
      )
      .subscribe((ecomOrders: IEcomOrder[]) => (this.ecomOrdersSharedCollection = ecomOrders));
  }

  protected createFromForm(): IEcomProduct {
    return {
      ...new EcomProduct(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      length: this.editForm.get(['length'])!.value,
      width: this.editForm.get(['width'])!.value,
      height: this.editForm.get(['height'])!.value,
      weight: this.editForm.get(['weight'])!.value,
      dimUnit: this.editForm.get(['dimUnit'])!.value,
      wghtUnit: this.editForm.get(['wghtUnit'])!.value,
      goodsValue: this.editForm.get(['goodsValue'])!.value,
      productValue: this.editForm.get(['productValue'])!.value,
      hsCodes: this.editForm.get(['hsCodes'])!.value,
      sku: this.editForm.get(['sku'])!.value,
      policy: this.editForm.get(['policy'])!.value,
      insuranceAmt: this.editForm.get(['insuranceAmt'])!.value,
      isInsured: this.editForm.get(['isInsured'])!.value,
      country: this.editForm.get(['country'])!.value,
      ecomWarehouses: this.editForm.get(['ecomWarehouses'])!.value,
      ecomOrder: this.editForm.get(['ecomOrder'])!.value,
    };
  }
}
