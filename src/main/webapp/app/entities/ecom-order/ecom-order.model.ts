import * as dayjs from 'dayjs';
import { IEcomProduct } from 'app/entities/ecom-product/ecom-product.model';
import { ICurrency } from 'app/entities/currency/currency.model';
import { IShippingAddress } from 'app/entities/shipping-address/shipping-address.model';
import { IEcomStore } from 'app/entities/ecom-store/ecom-store.model';

export interface IEcomOrder {
  id?: number;
  ecomOrderNumber?: number | null;
  customerName?: string | null;
  domainName?: string | null;
  name?: string | null;
  email?: string | null;
  gateway?: string | null;
  totalPrice?: number | null;
  subTotalPrice?: number | null;
  totalWeight?: number | null;
  totalTax?: number | null;
  fulfillmentStatus?: string | null;
  paymentStatus?: string | null;
  financialStatus?: string | null;
  createdDate?: dayjs.Dayjs | null;
  updatedDate?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  isCancelled?: boolean | null;
  isShipped?: boolean | null;
  eshipperStatus?: string | null;
  residentialShippingAddress?: boolean | null;
  shippingOrderRef?: number | null;
  fromEmail?: string | null;
  isCancelSchedule?: boolean | null;
  isSchedulePickup?: boolean | null;
  packageTypeId?: number | null;
  ecomProducts?: IEcomProduct[] | null;
  currency?: ICurrency | null;
  shippingAddress?: IShippingAddress | null;
  billingAddress?: IShippingAddress | null;
  ecomStore?: IEcomStore | null;
}

export class EcomOrder implements IEcomOrder {
  constructor(
    public id?: number,
    public ecomOrderNumber?: number | null,
    public customerName?: string | null,
    public domainName?: string | null,
    public name?: string | null,
    public email?: string | null,
    public gateway?: string | null,
    public totalPrice?: number | null,
    public subTotalPrice?: number | null,
    public totalWeight?: number | null,
    public totalTax?: number | null,
    public fulfillmentStatus?: string | null,
    public paymentStatus?: string | null,
    public financialStatus?: string | null,
    public createdDate?: dayjs.Dayjs | null,
    public updatedDate?: dayjs.Dayjs | null,
    public updatedBy?: string | null,
    public isCancelled?: boolean | null,
    public isShipped?: boolean | null,
    public eshipperStatus?: string | null,
    public residentialShippingAddress?: boolean | null,
    public shippingOrderRef?: number | null,
    public fromEmail?: string | null,
    public isCancelSchedule?: boolean | null,
    public isSchedulePickup?: boolean | null,
    public packageTypeId?: number | null,
    public ecomProducts?: IEcomProduct[] | null,
    public currency?: ICurrency | null,
    public shippingAddress?: IShippingAddress | null,
    public billingAddress?: IShippingAddress | null,
    public ecomStore?: IEcomStore | null
  ) {
    this.isCancelled = this.isCancelled ?? false;
    this.isShipped = this.isShipped ?? false;
    this.residentialShippingAddress = this.residentialShippingAddress ?? false;
    this.isCancelSchedule = this.isCancelSchedule ?? false;
    this.isSchedulePickup = this.isSchedulePickup ?? false;
  }
}

export function getEcomOrderIdentifier(ecomOrder: IEcomOrder): number | undefined {
  return ecomOrder.id;
}
