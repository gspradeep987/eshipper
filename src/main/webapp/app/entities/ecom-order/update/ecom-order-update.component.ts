import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IEcomOrder, EcomOrder } from '../ecom-order.model';
import { EcomOrderService } from '../service/ecom-order.service';
import { ICurrency } from 'app/entities/currency/currency.model';
import { CurrencyService } from 'app/entities/currency/service/currency.service';
import { IShippingAddress } from 'app/entities/shipping-address/shipping-address.model';
import { ShippingAddressService } from 'app/entities/shipping-address/service/shipping-address.service';
import { IEcomStore } from 'app/entities/ecom-store/ecom-store.model';
import { EcomStoreService } from 'app/entities/ecom-store/service/ecom-store.service';

@Component({
  selector: 'jhi-ecom-order-update',
  templateUrl: './ecom-order-update.component.html',
})
export class EcomOrderUpdateComponent implements OnInit {
  isSaving = false;

  currenciesSharedCollection: ICurrency[] = [];
  shippingAddressesSharedCollection: IShippingAddress[] = [];
  ecomStoresSharedCollection: IEcomStore[] = [];

  editForm = this.fb.group({
    id: [],
    ecomOrderNumber: [],
    customerName: [null, [Validators.maxLength(255)]],
    domainName: [null, [Validators.maxLength(255)]],
    name: [null, [Validators.maxLength(100)]],
    email: [null, [Validators.maxLength(100)]],
    gateway: [null, [Validators.maxLength(255)]],
    totalPrice: [],
    subTotalPrice: [],
    totalWeight: [],
    totalTax: [],
    fulfillmentStatus: [null, [Validators.maxLength(100)]],
    paymentStatus: [null, [Validators.maxLength(100)]],
    financialStatus: [null, [Validators.maxLength(100)]],
    createdDate: [],
    updatedDate: [],
    updatedBy: [null, [Validators.maxLength(100)]],
    isCancelled: [],
    isShipped: [],
    eshipperStatus: [null, [Validators.maxLength(100)]],
    residentialShippingAddress: [],
    shippingOrderRef: [],
    fromEmail: [null, [Validators.maxLength(100)]],
    isCancelSchedule: [],
    isSchedulePickup: [],
    packageTypeId: [],
    currency: [],
    shippingAddress: [],
    billingAddress: [],
    ecomStore: [],
  });

  constructor(
    protected ecomOrderService: EcomOrderService,
    protected currencyService: CurrencyService,
    protected shippingAddressService: ShippingAddressService,
    protected ecomStoreService: EcomStoreService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomOrder }) => {
      this.updateForm(ecomOrder);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomOrder = this.createFromForm();
    if (ecomOrder.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomOrderService.update(ecomOrder));
    } else {
      this.subscribeToSaveResponse(this.ecomOrderService.create(ecomOrder));
    }
  }

  trackCurrencyById(index: number, item: ICurrency): number {
    return item.id!;
  }

  trackShippingAddressById(index: number, item: IShippingAddress): number {
    return item.id!;
  }

  trackEcomStoreById(index: number, item: IEcomStore): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomOrder>>): void {
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

  protected updateForm(ecomOrder: IEcomOrder): void {
    this.editForm.patchValue({
      id: ecomOrder.id,
      ecomOrderNumber: ecomOrder.ecomOrderNumber,
      customerName: ecomOrder.customerName,
      domainName: ecomOrder.domainName,
      name: ecomOrder.name,
      email: ecomOrder.email,
      gateway: ecomOrder.gateway,
      totalPrice: ecomOrder.totalPrice,
      subTotalPrice: ecomOrder.subTotalPrice,
      totalWeight: ecomOrder.totalWeight,
      totalTax: ecomOrder.totalTax,
      fulfillmentStatus: ecomOrder.fulfillmentStatus,
      paymentStatus: ecomOrder.paymentStatus,
      financialStatus: ecomOrder.financialStatus,
      createdDate: ecomOrder.createdDate,
      updatedDate: ecomOrder.updatedDate,
      updatedBy: ecomOrder.updatedBy,
      isCancelled: ecomOrder.isCancelled,
      isShipped: ecomOrder.isShipped,
      eshipperStatus: ecomOrder.eshipperStatus,
      residentialShippingAddress: ecomOrder.residentialShippingAddress,
      shippingOrderRef: ecomOrder.shippingOrderRef,
      fromEmail: ecomOrder.fromEmail,
      isCancelSchedule: ecomOrder.isCancelSchedule,
      isSchedulePickup: ecomOrder.isSchedulePickup,
      packageTypeId: ecomOrder.packageTypeId,
      currency: ecomOrder.currency,
      shippingAddress: ecomOrder.shippingAddress,
      billingAddress: ecomOrder.billingAddress,
      ecomStore: ecomOrder.ecomStore,
    });

    this.currenciesSharedCollection = this.currencyService.addCurrencyToCollectionIfMissing(
      this.currenciesSharedCollection,
      ecomOrder.currency
    );
    this.shippingAddressesSharedCollection = this.shippingAddressService.addShippingAddressToCollectionIfMissing(
      this.shippingAddressesSharedCollection,
      ecomOrder.shippingAddress,
      ecomOrder.billingAddress
    );
    this.ecomStoresSharedCollection = this.ecomStoreService.addEcomStoreToCollectionIfMissing(
      this.ecomStoresSharedCollection,
      ecomOrder.ecomStore
    );
  }

  protected loadRelationshipsOptions(): void {
    this.currencyService
      .query()
      .pipe(map((res: HttpResponse<ICurrency[]>) => res.body ?? []))
      .pipe(
        map((currencies: ICurrency[]) =>
          this.currencyService.addCurrencyToCollectionIfMissing(currencies, this.editForm.get('currency')!.value)
        )
      )
      .subscribe((currencies: ICurrency[]) => (this.currenciesSharedCollection = currencies));

    this.shippingAddressService
      .query()
      .pipe(map((res: HttpResponse<IShippingAddress[]>) => res.body ?? []))
      .pipe(
        map((shippingAddresses: IShippingAddress[]) =>
          this.shippingAddressService.addShippingAddressToCollectionIfMissing(
            shippingAddresses,
            this.editForm.get('shippingAddress')!.value,
            this.editForm.get('billingAddress')!.value
          )
        )
      )
      .subscribe((shippingAddresses: IShippingAddress[]) => (this.shippingAddressesSharedCollection = shippingAddresses));

    this.ecomStoreService
      .query()
      .pipe(map((res: HttpResponse<IEcomStore[]>) => res.body ?? []))
      .pipe(
        map((ecomStores: IEcomStore[]) =>
          this.ecomStoreService.addEcomStoreToCollectionIfMissing(ecomStores, this.editForm.get('ecomStore')!.value)
        )
      )
      .subscribe((ecomStores: IEcomStore[]) => (this.ecomStoresSharedCollection = ecomStores));
  }

  protected createFromForm(): IEcomOrder {
    return {
      ...new EcomOrder(),
      id: this.editForm.get(['id'])!.value,
      ecomOrderNumber: this.editForm.get(['ecomOrderNumber'])!.value,
      customerName: this.editForm.get(['customerName'])!.value,
      domainName: this.editForm.get(['domainName'])!.value,
      name: this.editForm.get(['name'])!.value,
      email: this.editForm.get(['email'])!.value,
      gateway: this.editForm.get(['gateway'])!.value,
      totalPrice: this.editForm.get(['totalPrice'])!.value,
      subTotalPrice: this.editForm.get(['subTotalPrice'])!.value,
      totalWeight: this.editForm.get(['totalWeight'])!.value,
      totalTax: this.editForm.get(['totalTax'])!.value,
      fulfillmentStatus: this.editForm.get(['fulfillmentStatus'])!.value,
      paymentStatus: this.editForm.get(['paymentStatus'])!.value,
      financialStatus: this.editForm.get(['financialStatus'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value,
      updatedDate: this.editForm.get(['updatedDate'])!.value,
      updatedBy: this.editForm.get(['updatedBy'])!.value,
      isCancelled: this.editForm.get(['isCancelled'])!.value,
      isShipped: this.editForm.get(['isShipped'])!.value,
      eshipperStatus: this.editForm.get(['eshipperStatus'])!.value,
      residentialShippingAddress: this.editForm.get(['residentialShippingAddress'])!.value,
      shippingOrderRef: this.editForm.get(['shippingOrderRef'])!.value,
      fromEmail: this.editForm.get(['fromEmail'])!.value,
      isCancelSchedule: this.editForm.get(['isCancelSchedule'])!.value,
      isSchedulePickup: this.editForm.get(['isSchedulePickup'])!.value,
      packageTypeId: this.editForm.get(['packageTypeId'])!.value,
      currency: this.editForm.get(['currency'])!.value,
      shippingAddress: this.editForm.get(['shippingAddress'])!.value,
      billingAddress: this.editForm.get(['billingAddress'])!.value,
      ecomStore: this.editForm.get(['ecomStore'])!.value,
    };
  }
}
