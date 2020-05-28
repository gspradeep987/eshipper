import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'ecom-store',
        loadChildren: () => import('./ecom-store/ecom-store.module').then(m => m.EshipperEcomStoreModule),
      },
      {
        path: 'ecom-store-address',
        loadChildren: () => import('./ecom-store-address/ecom-store-address.module').then(m => m.EshipperEcomStoreAddressModule),
      },
      {
        path: 'ecom-store-color-theme',
        loadChildren: () => import('./ecom-store-color-theme/ecom-store-color-theme.module').then(m => m.EshipperEcomStoreColorThemeModule),
      },
      {
        path: 'shipment-service',
        loadChildren: () => import('./shipment-service/shipment-service.module').then(m => m.EshipperShipmentServiceModule),
      },
      {
        path: 'ecom-store-shipment-settings',
        loadChildren: () =>
          import('./ecom-store-shipment-settings/ecom-store-shipment-settings.module').then(m => m.EshipperEcomStoreShipmentSettingsModule),
      },
      {
        path: 'ecom-store-package-settings',
        loadChildren: () =>
          import('./ecom-store-package-settings/ecom-store-package-settings.module').then(m => m.EshipperEcomStorePackageSettingsModule),
      },
      {
        path: 'ecom-store-markup',
        loadChildren: () => import('./ecom-store-markup/ecom-store-markup.module').then(m => m.EshipperEcomStoreMarkupModule),
      },
      {
        path: 'ecom-markup-primary',
        loadChildren: () => import('./ecom-markup-primary/ecom-markup-primary.module').then(m => m.EshipperEcomMarkupPrimaryModule),
      },
      {
        path: 'ecom-markup-secondary',
        loadChildren: () => import('./ecom-markup-secondary/ecom-markup-secondary.module').then(m => m.EshipperEcomMarkupSecondaryModule),
      },
      {
        path: 'ecom-markup-tertiary',
        loadChildren: () => import('./ecom-markup-tertiary/ecom-markup-tertiary.module').then(m => m.EshipperEcomMarkupTertiaryModule),
      },
      {
        path: 'ecom-markup-quaternary',
        loadChildren: () =>
          import('./ecom-markup-quaternary/ecom-markup-quaternary.module').then(m => m.EshipperEcomMarkupQuaternaryModule),
      },
      {
        path: 'ecom-mail-template',
        loadChildren: () => import('./ecom-mail-template/ecom-mail-template.module').then(m => m.EshipperEcomMailTemplateModule),
      },
      {
        path: 'ecom-product',
        loadChildren: () => import('./ecom-product/ecom-product.module').then(m => m.EshipperEcomProductModule),
      },
      {
        path: 'ecom-product-image',
        loadChildren: () => import('./ecom-product-image/ecom-product-image.module').then(m => m.EshipperEcomProductImageModule),
      },
      {
        path: 'ecom-warehouse',
        loadChildren: () => import('./ecom-warehouse/ecom-warehouse.module').then(m => m.EshipperEcomWarehouseModule),
      },
      {
        path: 'ecom-order',
        loadChildren: () => import('./ecom-order/ecom-order.module').then(m => m.EshipperEcomOrderModule),
      },
      {
        path: 'currency',
        loadChildren: () => import('./currency/currency.module').then(m => m.EshipperCurrencyModule),
      },
      {
        path: 'company',
        loadChildren: () => import('./company/company.module').then(m => m.EshipperCompanyModule),
      },
      {
        path: 'country',
        loadChildren: () => import('./country/country.module').then(m => m.EshipperCountryModule),
      },
      {
        path: 'shipping-address',
        loadChildren: () => import('./shipping-address/shipping-address.module').then(m => m.EshipperShippingAddressModule),
      },
      {
        path: 'province',
        loadChildren: () => import('./province/province.module').then(m => m.EshipperProvinceModule),
      },
      {
        path: 'city',
        loadChildren: () => import('./city/city.module').then(m => m.EshipperCityModule),
      },
      {
        path: 'ecom-store-sync',
        loadChildren: () => import('./ecom-store-sync/ecom-store-sync.module').then(m => m.EshipperEcomStoreSyncModule),
      },
      {
        path: 'wo-sales-agent',
        loadChildren: () => import('./wo-sales-agent/wo-sales-agent.module').then(m => m.EshipperWoSalesAgentModule),
      },
      {
        path: 'sales-agent-type',
        loadChildren: () => import('./sales-agent-type/sales-agent-type.module').then(m => m.EshipperSalesAgentTypeModule),
      },
      {
        path: 'wo-sales-agent-details',
        loadChildren: () => import('./wo-sales-agent-details/wo-sales-agent-details.module').then(m => m.EshipperWoSalesAgentDetailsModule),
      },
      {
        path: 'payment-method',
        loadChildren: () => import('./payment-method/payment-method.module').then(m => m.EshipperPaymentMethodModule),
      },
      {
        path: 'wo-sales-commission-details',
        loadChildren: () =>
          import('./wo-sales-commission-details/wo-sales-commission-details.module').then(m => m.EshipperWoSalesCommissionDetailsModule),
      },
      {
        path: 'wo-sales-commission-carrier',
        loadChildren: () =>
          import('./wo-sales-commission-carrier/wo-sales-commission-carrier.module').then(m => m.EshipperWoSalesCommissionCarrierModule),
      },
      {
        path: 'wo-sales-operational-details',
        loadChildren: () =>
          import('./wo-sales-operational-details/wo-sales-operational-details.module').then(m => m.EshipperWoSalesOperationalDetailsModule),
      },
      {
        path: 'wo-sales-operational-carrier',
        loadChildren: () =>
          import('./wo-sales-operational-carrier/wo-sales-operational-carrier.module').then(m => m.EshipperWoSalesOperationalCarrierModule),
      },
      {
        path: 'wo-sales-commission-transfer',
        loadChildren: () =>
          import('./wo-sales-commission-transfer/wo-sales-commission-transfer.module').then(m => m.EshipperWoSalesCommissionTransferModule),
      },
      {
        path: 'carrier',
        loadChildren: () => import('./carrier/carrier.module').then(m => m.EshipperCarrierModule),
      },
      {
        path: 'carrier-service',
        loadChildren: () => import('./carrier-service/carrier-service.module').then(m => m.EshipperCarrierServiceModule),
      },
      {
        path: 'user-10',
        loadChildren: () => import('./user-10/user-10.module').then(m => m.EshipperUser10Module),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EshipperEntityModule {}
