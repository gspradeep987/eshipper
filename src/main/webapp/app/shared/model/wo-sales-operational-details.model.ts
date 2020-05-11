import { IWoSalesOperationalCarrier } from 'app/shared/model/wo-sales-operational-carrier.model';

export interface IWoSalesOperationalDetails {
  id?: number;
  defaultOpExpense?: number;
  opExpPalletShip?: number;
  opExpPackageShip?: number;
  opExpPack?: number;
  opExpSmartePost?: number;
  woSalesOperationalCarriers?: IWoSalesOperationalCarrier[];
  woSalesAgentId?: number;
}

export class WoSalesOperationalDetails implements IWoSalesOperationalDetails {
  constructor(
    public id?: number,
    public defaultOpExpense?: number,
    public opExpPalletShip?: number,
    public opExpPackageShip?: number,
    public opExpPack?: number,
    public opExpSmartePost?: number,
    public woSalesOperationalCarriers?: IWoSalesOperationalCarrier[],
    public woSalesAgentId?: number
  ) {}
}
