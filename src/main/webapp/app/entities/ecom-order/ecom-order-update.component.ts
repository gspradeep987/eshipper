import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomOrder, EcomOrder } from 'app/shared/model/ecom-order.model';
import { EcomOrderService } from './ecom-order.service';
import { ICurrency } from 'app/shared/model/currency.model';
import { CurrencyService } from 'app/entities/currency/currency.service';
import { IShippingAddress } from 'app/shared/model/shipping-address.model';
import { ShippingAddressService } from 'app/entities/shipping-address/shipping-address.service';
import { IEcomStore } from 'app/shared/model/ecom-store.model';
import { EcomStoreService } from 'app/entities/ecom-store/ecom-store.service';

type SelectableEntity = ICurrency | IShippingAddress | IEcomStore;

@Component({
  selector: 'jhi-ecom-order-update',
  templateUrl: './ecom-order-update.component.html'
})
export class EcomOrderUpdateComponent implements OnInit {
  isSaving = false;
  currencies: ICurrency[] = [];
  shippingaddresses: IShippingAddress[] = [];
  ecomstores: IEcomStore[] = [];
  createdDateDp: any;
  updatedDateDp: any;

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
    currencyId: [],
    shippingAddressId: [],
    billingAddressId: [],
    ecomStoreId: []
  });

  constructor(
    protected ecomOrderService: EcomOrderService,
    protected currencyService: CurrencyService,
    protected shippingAddressService: ShippingAddressService,
    protected ecomStoreService: EcomStoreService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomOrder }) => {
      this.updateForm(ecomOrder);

      this.currencyService.query().subscribe((res: HttpResponse<ICurrency[]>) => (this.currencies = res.body || []));

      this.shippingAddressService.query().subscribe((res: HttpResponse<IShippingAddress[]>) => (this.shippingaddresses = res.body || []));

      this.ecomStoreService.query().subscribe((res: HttpResponse<IEcomStore[]>) => (this.ecomstores = res.body || []));
    });
  }

  updateForm(ecomOrder: IEcomOrder): void {
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
      currencyId: ecomOrder.currencyId,
      shippingAddressId: ecomOrder.shippingAddressId,
      billingAddressId: ecomOrder.billingAddressId,
      ecomStoreId: ecomOrder.ecomStoreId
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

  private createFromForm(): IEcomOrder {
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
      currencyId: this.editForm.get(['currencyId'])!.value,
      shippingAddressId: this.editForm.get(['shippingAddressId'])!.value,
      billingAddressId: this.editForm.get(['billingAddressId'])!.value,
      ecomStoreId: this.editForm.get(['ecomStoreId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomOrder>>): void {
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
}
