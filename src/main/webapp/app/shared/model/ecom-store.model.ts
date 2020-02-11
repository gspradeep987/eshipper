import { Moment } from 'moment';
import { IEcomMailTemplate } from 'app/shared/model/ecom-mail-template.model';
import { IEcomOrder } from 'app/shared/model/ecom-order.model';
import { IShipmentService } from 'app/shared/model/shipment-service.model';

export interface IEcomStore {
  id?: number;
  name?: string;
  nickName?: string;
  apiPassword?: string;
  domain?: string;
  syncFrequency?: number;
  syncInterval?: string;
  active?: boolean;
  isManualSync?: boolean;
  createdDate?: Moment;
  updatedDate?: Moment;
  ecomStoreAddressId?: number;
  ecomStoreColorThemeId?: number;
  ecomStoreShipmentSettingsId?: number;
  ecomStorePackageSettingsId?: number;
  ecomStoreMarkupId?: number;
  ecomMailTemplates?: IEcomMailTemplate[];
  ecomOrders?: IEcomOrder[];
  companyId?: number;
  userId?: number;
  ecomProductId?: number;
  shipmentServices?: IShipmentService[];
}

export class EcomStore implements IEcomStore {
  constructor(
    public id?: number,
    public name?: string,
    public nickName?: string,
    public apiPassword?: string,
    public domain?: string,
    public syncFrequency?: number,
    public syncInterval?: string,
    public active?: boolean,
    public isManualSync?: boolean,
    public createdDate?: Moment,
    public updatedDate?: Moment,
    public ecomStoreAddressId?: number,
    public ecomStoreColorThemeId?: number,
    public ecomStoreShipmentSettingsId?: number,
    public ecomStorePackageSettingsId?: number,
    public ecomStoreMarkupId?: number,
    public ecomMailTemplates?: IEcomMailTemplate[],
    public ecomOrders?: IEcomOrder[],
    public companyId?: number,
    public userId?: number,
    public ecomProductId?: number,
    public shipmentServices?: IShipmentService[]
  ) {
    this.active = this.active || false;
    this.isManualSync = this.isManualSync || false;
  }
}
