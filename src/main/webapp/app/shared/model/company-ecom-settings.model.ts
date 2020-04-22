import { ICarrierSettings } from 'app/shared/model/carrier-settings.model';

export interface ICompanyEcomSettings {
  id?: number;
  notifyRecipient?: boolean;
  createPackingList?: boolean;
  createPackingSlip?: boolean;
  ecomModule?: boolean;
  includeTaxesInRvenues?: boolean;
  showAvgCustomerValue?: boolean;
  showAvgOrderValue?: boolean;
  showAvgShipmentValue?: boolean;
  removeSerialReturners?: boolean;
  showPackageStatistics?: boolean;
  includeAllItemsRetCustomers?: boolean;
  signatureRequiredId?: number;
  carrierSettings?: ICarrierSettings[];
}

export class CompanyEcomSettings implements ICompanyEcomSettings {
  constructor(
    public id?: number,
    public notifyRecipient?: boolean,
    public createPackingList?: boolean,
    public createPackingSlip?: boolean,
    public ecomModule?: boolean,
    public includeTaxesInRvenues?: boolean,
    public showAvgCustomerValue?: boolean,
    public showAvgOrderValue?: boolean,
    public showAvgShipmentValue?: boolean,
    public removeSerialReturners?: boolean,
    public showPackageStatistics?: boolean,
    public includeAllItemsRetCustomers?: boolean,
    public signatureRequiredId?: number,
    public carrierSettings?: ICarrierSettings[]
  ) {
    this.notifyRecipient = this.notifyRecipient || false;
    this.createPackingList = this.createPackingList || false;
    this.createPackingSlip = this.createPackingSlip || false;
    this.ecomModule = this.ecomModule || false;
    this.includeTaxesInRvenues = this.includeTaxesInRvenues || false;
    this.showAvgCustomerValue = this.showAvgCustomerValue || false;
    this.showAvgOrderValue = this.showAvgOrderValue || false;
    this.showAvgShipmentValue = this.showAvgShipmentValue || false;
    this.removeSerialReturners = this.removeSerialReturners || false;
    this.showPackageStatistics = this.showPackageStatistics || false;
    this.includeAllItemsRetCustomers = this.includeAllItemsRetCustomers || false;
  }
}
