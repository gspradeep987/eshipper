export interface IElasticSearchWoSalesAgent {
  id?: number;
  statusId?: number;
  woSalesAgentId?: number;
}

export class ElasticSearchWoSalesAgent implements IElasticSearchWoSalesAgent {
  constructor(public id?: number, public statusId?: number, public woSalesAgentId?: number) {}
}
