import { Moment } from 'moment';

export interface IEcomAutomationRules {
  id?: number;
  name?: string;
  enable?: boolean;
  createdDate?: Moment;
  createdBy?: string;
  ecomStoreId?: number;
}

export class EcomAutomationRules implements IEcomAutomationRules {
  constructor(
    public id?: number,
    public name?: string,
    public enable?: boolean,
    public createdDate?: Moment,
    public createdBy?: string,
    public ecomStoreId?: number
  ) {
    this.enable = this.enable || false;
  }
}
