export interface IWoSalesAgentDetails {
  id?: number;
  hstNumber?: number;
  promoCode?: string;
  promoUrl?: string;
  woSalesAgentId?: number;
}

export class WoSalesAgentDetails implements IWoSalesAgentDetails {
  constructor(
    public id?: number,
    public hstNumber?: number,
    public promoCode?: string,
    public promoUrl?: string,
    public woSalesAgentId?: number
  ) {}
}
