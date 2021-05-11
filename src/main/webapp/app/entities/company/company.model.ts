export interface ICompany {
  id?: number;
}

export class Company implements ICompany {
  constructor(public id?: number) {}
}

export function getCompanyIdentifier(company: ICompany): number | undefined {
  return company.id;
}
