export interface IWoSalesAgent {
  id?: number;
  isActive?: boolean;
  elasticSearchWoSalesAgentId?: number;
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
    public elasticSearchWoSalesAgentId?: number,
    public woSalesAgentDetailsId?: number,
    public woSalesCommissionDetailsId?: number,
    public woSalesOperationalDetailsId?: number,
    public woSalesCommissionTransferId?: number,
    public salesAgentTypeId?: number
  ) {
    this.isActive = this.isActive || false;
  }
}
