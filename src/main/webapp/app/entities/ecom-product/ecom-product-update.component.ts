import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomProduct, EcomProduct } from 'app/shared/model/ecom-product.model';
import { EcomProductService } from './ecom-product.service';
import { ICountry } from 'app/shared/model/country.model';
import { CountryService } from 'app/entities/country/country.service';
import { IEcomWarehouse } from 'app/shared/model/ecom-warehouse.model';
import { EcomWarehouseService } from 'app/entities/ecom-warehouse/ecom-warehouse.service';
import { IEcomOrder } from 'app/shared/model/ecom-order.model';
import { EcomOrderService } from 'app/entities/ecom-order/ecom-order.service';

type SelectableEntity = ICountry | IEcomWarehouse | IEcomOrder;

@Component({
  selector: 'jhi-ecom-product-update',
  templateUrl: './ecom-product-update.component.html'
})
export class EcomProductUpdateComponent implements OnInit {
  isSaving = false;
  countries: ICountry[] = [];
  ecomwarehouses: IEcomWarehouse[] = [];
  ecomorders: IEcomOrder[] = [];

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
    countryId: [],
    ecomWarehouses: [],
    ecomOrderId: []
  });

  constructor(
    protected ecomProductService: EcomProductService,
    protected countryService: CountryService,
    protected ecomWarehouseService: EcomWarehouseService,
    protected ecomOrderService: EcomOrderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomProduct }) => {
      this.updateForm(ecomProduct);

      this.countryService.query().subscribe((res: HttpResponse<ICountry[]>) => (this.countries = res.body || []));

      this.ecomWarehouseService.query().subscribe((res: HttpResponse<IEcomWarehouse[]>) => (this.ecomwarehouses = res.body || []));

      this.ecomOrderService.query().subscribe((res: HttpResponse<IEcomOrder[]>) => (this.ecomorders = res.body || []));
    });
  }

  updateForm(ecomProduct: IEcomProduct): void {
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
      countryId: ecomProduct.countryId,
      ecomWarehouses: ecomProduct.ecomWarehouses,
      ecomOrderId: ecomProduct.ecomOrderId
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

  private createFromForm(): IEcomProduct {
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
      countryId: this.editForm.get(['countryId'])!.value,
      ecomWarehouses: this.editForm.get(['ecomWarehouses'])!.value,
      ecomOrderId: this.editForm.get(['ecomOrderId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomProduct>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: IEcomWarehouse[], option: IEcomWarehouse): IEcomWarehouse {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
