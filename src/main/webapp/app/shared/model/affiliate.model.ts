import { Moment } from 'moment';
import { ICompany } from 'app/shared/model/company.model';

export interface IAffiliate {
  id?: number;
  isActive?: boolean;
  notifyUser?: boolean;
  promoCode?: string;
  promoCodeUrl?: string;
  commissionPercentage?: number;
  commissionDate?: Moment;
  createdDate?: Moment;
  updatedDate?: Moment;
  affiliatedCustomers?: ICompany[];
  paymentMethodId?: number;
  affiliateId?: number;
  franchiseId?: number;
}

export class Affiliate implements IAffiliate {
  constructor(
    public id?: number,
    public isActive?: boolean,
    public notifyUser?: boolean,
    public promoCode?: string,
    public promoCodeUrl?: string,
    public commissionPercentage?: number,
    public commissionDate?: Moment,
    public createdDate?: Moment,
    public updatedDate?: Moment,
    public affiliatedCustomers?: ICompany[],
    public paymentMethodId?: number,
    public affiliateId?: number,
    public franchiseId?: number
  ) {
    this.isActive = this.isActive || false;
    this.notifyUser = this.notifyUser || false;
  }
}
