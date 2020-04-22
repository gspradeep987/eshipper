import { ICompanyEcomSettings } from 'app/shared/model/company-ecom-settings.model';

export interface ICarrierSettings {
  id?: number;
  companyEcomSettings?: ICompanyEcomSettings[];
}

export class CarrierSettings implements ICarrierSettings {
  constructor(public id?: number, public companyEcomSettings?: ICompanyEcomSettings[]) {}
}
