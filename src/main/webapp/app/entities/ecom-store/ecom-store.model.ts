import * as dayjs from 'dayjs';
import { IEcomStoreAddress } from 'app/entities/ecom-store-address/ecom-store-address.model';
import { IEcomStoreColorTheme } from 'app/entities/ecom-store-color-theme/ecom-store-color-theme.model';
import { IEcomStoreShipmentSettings } from 'app/entities/ecom-store-shipment-settings/ecom-store-shipment-settings.model';
import { IEcomStorePackageSettings } from 'app/entities/ecom-store-package-settings/ecom-store-package-settings.model';
import { IEcomStoreMarkup } from 'app/entities/ecom-store-markup/ecom-store-markup.model';
import { IEcomMailTemplate } from 'app/entities/ecom-mail-template/ecom-mail-template.model';
import { IEcomOrder } from 'app/entities/ecom-order/ecom-order.model';
import { ICompany } from 'app/entities/company/company.model';
import { IUser } from 'app/entities/user/user.model';
import { IEcomProduct } from 'app/entities/ecom-product/ecom-product.model';
import { IShipmentService } from 'app/entities/shipment-service/shipment-service.model';

export interface IEcomStore {
  id?: number;
  name?: string | null;
  nickName?: string | null;
  apiPassword?: string | null;
  domain?: string | null;
  syncFrequency?: number | null;
  syncInterval?: string | null;
  active?: boolean | null;
  isManualSync?: boolean | null;
  createdDate?: dayjs.Dayjs | null;
  updatedDate?: dayjs.Dayjs | null;
  ecomStoreAddress?: IEcomStoreAddress | null;
  ecomStoreColorTheme?: IEcomStoreColorTheme | null;
  ecomStoreShipmentSettings?: IEcomStoreShipmentSettings | null;
  ecomStorePackageSettings?: IEcomStorePackageSettings | null;
  ecomStoreMarkup?: IEcomStoreMarkup | null;
  ecomMailTemplates?: IEcomMailTemplate[] | null;
  ecomOrders?: IEcomOrder[] | null;
  company?: ICompany | null;
  user?: IUser | null;
  ecomProduct?: IEcomProduct | null;
  shipmentServices?: IShipmentService[] | null;
}

export class EcomStore implements IEcomStore {
  constructor(
    public id?: number,
    public name?: string | null,
    public nickName?: string | null,
    public apiPassword?: string | null,
    public domain?: string | null,
    public syncFrequency?: number | null,
    public syncInterval?: string | null,
    public active?: boolean | null,
    public isManualSync?: boolean | null,
    public createdDate?: dayjs.Dayjs | null,
    public updatedDate?: dayjs.Dayjs | null,
    public ecomStoreAddress?: IEcomStoreAddress | null,
    public ecomStoreColorTheme?: IEcomStoreColorTheme | null,
    public ecomStoreShipmentSettings?: IEcomStoreShipmentSettings | null,
    public ecomStorePackageSettings?: IEcomStorePackageSettings | null,
    public ecomStoreMarkup?: IEcomStoreMarkup | null,
    public ecomMailTemplates?: IEcomMailTemplate[] | null,
    public ecomOrders?: IEcomOrder[] | null,
    public company?: ICompany | null,
    public user?: IUser | null,
    public ecomProduct?: IEcomProduct | null,
    public shipmentServices?: IShipmentService[] | null
  ) {
    this.active = this.active ?? false;
    this.isManualSync = this.isManualSync ?? false;
  }
}

export function getEcomStoreIdentifier(ecomStore: IEcomStore): number | undefined {
  return ecomStore.id;
}
