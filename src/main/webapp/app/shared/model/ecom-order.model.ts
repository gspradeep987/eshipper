import { Moment } from 'moment';
import { IEcomProduct } from 'app/shared/model/ecom-product.model';

export interface IEcomOrder {
  id?: number;
  ecomOrderNumber?: number;
  customerName?: string;
  domainName?: string;
  name?: string;
  email?: string;
  gateway?: string;
  totalPrice?: number;
  subTotalPrice?: number;
  totalWeight?: number;
  totalTax?: number;
  fulfillmentStatus?: string;
  paymentStatus?: string;
  financialStatus?: string;
  createdDate?: Moment;
  updatedDate?: Moment;
  updatedBy?: string;
  isCancelled?: boolean;
  isShipped?: boolean;
  eshipperStatus?: string;
  residentialShippingAddress?: boolean;
  shippingOrderRef?: number;
  fromEmail?: string;
  isCancelSchedule?: boolean;
  isSchedulePickup?: boolean;
  packageTypeId?: number;
  ecomProducts?: IEcomProduct[];
  currencyId?: number;
  shippingAddressId?: number;
  billingAddressId?: number;
  ecomStoreId?: number;
}

export class EcomOrder implements IEcomOrder {
  constructor(
    public id?: number,
    public ecomOrderNumber?: number,
    public customerName?: string,
    public domainName?: string,
    public name?: string,
    public email?: string,
    public gateway?: string,
    public totalPrice?: number,
    public subTotalPrice?: number,
    public totalWeight?: number,
    public totalTax?: number,
    public fulfillmentStatus?: string,
    public paymentStatus?: string,
    public financialStatus?: string,
    public createdDate?: Moment,
    public updatedDate?: Moment,
    public updatedBy?: string,
    public isCancelled?: boolean,
    public isShipped?: boolean,
    public eshipperStatus?: string,
    public residentialShippingAddress?: boolean,
    public shippingOrderRef?: number,
    public fromEmail?: string,
    public isCancelSchedule?: boolean,
    public isSchedulePickup?: boolean,
    public packageTypeId?: number,
    public ecomProducts?: IEcomProduct[],
    public currencyId?: number,
    public shippingAddressId?: number,
    public billingAddressId?: number,
    public ecomStoreId?: number
  ) {
    this.isCancelled = this.isCancelled || false;
    this.isShipped = this.isShipped || false;
    this.residentialShippingAddress = this.residentialShippingAddress || false;
    this.isCancelSchedule = this.isCancelSchedule || false;
    this.isSchedulePickup = this.isSchedulePickup || false;
  }
}
