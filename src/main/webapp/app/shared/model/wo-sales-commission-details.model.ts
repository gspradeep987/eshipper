import { IWoSalesCommissionCarrier } from 'app/shared/model/wo-sales-commission-carrier.model';

export interface IWoSalesCommissionDetails {
  id?: number;
  commission?: number;
  woSalesCommissionCarriers?: IWoSalesCommissionCarrier[];
  woSalesAgentId?: number;
}

export class WoSalesCommissionDetails implements IWoSalesCommissionDetails {
  constructor(
    public id?: number,
    public commission?: number,
    public woSalesCommissionCarriers?: IWoSalesCommissionCarrier[],
    public woSalesAgentId?: number
  ) {}
}
