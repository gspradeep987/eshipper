import { Moment } from 'moment';

export interface IWoSalesCommissionTransfer {
  id?: number;
  customerTransferDate?: Moment;
  isIncludeHistoricalData?: boolean;
  woSalesAgentId?: number;
  woSalesAgentId?: number;
}

export class WoSalesCommissionTransfer implements IWoSalesCommissionTransfer {
  constructor(
    public id?: number,
    public customerTransferDate?: Moment,
    public isIncludeHistoricalData?: boolean,
    public woSalesAgentId?: number,
    public woSalesAgentId?: number
  ) {
    this.isIncludeHistoricalData = this.isIncludeHistoricalData || false;
  }
}
