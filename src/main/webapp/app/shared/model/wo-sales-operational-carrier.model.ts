export interface IWoSalesOperationalCarrier {
  id?: number;
  opExp?: number;
  carrierId?: number;
  carrierServiceId?: number;
  woSalesOperationalDetailsId?: number;
}

export class WoSalesOperationalCarrier implements IWoSalesOperationalCarrier {
  constructor(
    public id?: number,
    public opExp?: number,
    public carrierId?: number,
    public carrierServiceId?: number,
    public woSalesOperationalDetailsId?: number
  ) {}
}
