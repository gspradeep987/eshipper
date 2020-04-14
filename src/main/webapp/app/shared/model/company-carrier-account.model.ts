import { AccountOwner } from 'app/shared/model/enumerations/account-owner.model';

export interface ICompanyCarrierAccount {
  id?: number;
  accountOwner?: AccountOwner;
  accountNumber?: number;
  meterNumber?: string;
  key?: string;
  password?: string;
  regionId?: number;
  carrierId?: number;
  franchiseId?: number;
}

export class CompanyCarrierAccount implements ICompanyCarrierAccount {
  constructor(
    public id?: number,
    public accountOwner?: AccountOwner,
    public accountNumber?: number,
    public meterNumber?: string,
    public key?: string,
    public password?: string,
    public regionId?: number,
    public carrierId?: number,
    public franchiseId?: number
  ) {}
}
