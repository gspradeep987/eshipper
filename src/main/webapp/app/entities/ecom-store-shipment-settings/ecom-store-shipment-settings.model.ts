import { IEcomStore } from 'app/entities/ecom-store/ecom-store.model';

export interface IEcomStoreShipmentSettings {
  id?: number;
  shippingMode?: string | null;
  signatureReqd?: string | null;
  schedulePickup?: boolean | null;
  liveRates?: boolean | null;
  addResidential?: boolean | null;
  tailgateAtDestination?: boolean | null;
  tailgateAtSource?: boolean | null;
  ecomStore?: IEcomStore | null;
}

export class EcomStoreShipmentSettings implements IEcomStoreShipmentSettings {
  constructor(
    public id?: number,
    public shippingMode?: string | null,
    public signatureReqd?: string | null,
    public schedulePickup?: boolean | null,
    public liveRates?: boolean | null,
    public addResidential?: boolean | null,
    public tailgateAtDestination?: boolean | null,
    public tailgateAtSource?: boolean | null,
    public ecomStore?: IEcomStore | null
  ) {
    this.schedulePickup = this.schedulePickup ?? false;
    this.liveRates = this.liveRates ?? false;
    this.addResidential = this.addResidential ?? false;
    this.tailgateAtDestination = this.tailgateAtDestination ?? false;
    this.tailgateAtSource = this.tailgateAtSource ?? false;
  }
}

export function getEcomStoreShipmentSettingsIdentifier(ecomStoreShipmentSettings: IEcomStoreShipmentSettings): number | undefined {
  return ecomStoreShipmentSettings.id;
}
