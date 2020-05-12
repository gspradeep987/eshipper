export interface IWoSalesCommissionCarrier {
  id?: number;
  commissionPercentageByCarrier?: number;
  carrierId?: number;
  carrierServiceId?: number;
  woSalesCommissionDetailsId?: number;
}

export class WoSalesCommissionCarrier implements IWoSalesCommissionCarrier {
  constructor(
    public id?: number,
    public commissionPercentageByCarrier?: number,
    public carrierId?: number,
    public carrierServiceId?: number,
    public woSalesCommissionDetailsId?: number
  ) {}
}
