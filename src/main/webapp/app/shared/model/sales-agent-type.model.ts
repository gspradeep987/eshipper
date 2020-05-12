export interface ISalesAgentType {
  id?: number;
  agentType?: string;
}

export class SalesAgentType implements ISalesAgentType {
  constructor(public id?: number, public agentType?: string) {}
}
