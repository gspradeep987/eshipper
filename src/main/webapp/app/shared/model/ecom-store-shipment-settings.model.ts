export interface IEcomStoreShipmentSettings {
  id?: number;
  shippingMode?: string;
  signatureReqd?: string;
  schedulePickup?: boolean;
  liveRates?: boolean;
  addResidential?: boolean;
  tailgateAtDestination?: boolean;
  tailgateAtSource?: boolean;
  ecomStoreId?: number;
}

export class EcomStoreShipmentSettings implements IEcomStoreShipmentSettings {
  constructor(
    public id?: number,
    public shippingMode?: string,
    public signatureReqd?: string,
    public schedulePickup?: boolean,
    public liveRates?: boolean,
    public addResidential?: boolean,
    public tailgateAtDestination?: boolean,
    public tailgateAtSource?: boolean,
    public ecomStoreId?: number
  ) {
    this.schedulePickup = this.schedulePickup || false;
    this.liveRates = this.liveRates || false;
    this.addResidential = this.addResidential || false;
    this.tailgateAtDestination = this.tailgateAtDestination || false;
    this.tailgateAtSource = this.tailgateAtSource || false;
  }
}
