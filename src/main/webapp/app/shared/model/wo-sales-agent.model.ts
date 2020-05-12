export interface IWoSalesAgent {
  id?: number;
  isActive?: boolean;
  woSalesAgentDetailsId?: number;
  woSalesCommissionDetailsId?: number;
  woSalesOperationalDetailsId?: number;
  woSalesCommissionTransferId?: number;
  salesAgentTypeId?: number;
}

export class WoSalesAgent implements IWoSalesAgent {
  constructor(
    public id?: number,
    public isActive?: boolean,
    public woSalesAgentDetailsId?: number,
    public woSalesCommissionDetailsId?: number,
    public woSalesOperationalDetailsId?: number,
    public woSalesCommissionTransferId?: number,
    public salesAgentTypeId?: number
  ) {
    this.isActive = this.isActive || false;
  }
}
