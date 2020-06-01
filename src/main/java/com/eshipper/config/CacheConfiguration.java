package com.eshipper.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.eshipper.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.eshipper.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.eshipper.domain.User.class.getName());
            createCache(cm, com.eshipper.domain.Authority.class.getName());
            createCache(cm, com.eshipper.domain.User.class.getName() + ".authorities");
            createCache(cm, com.eshipper.domain.EcomStore.class.getName());
            createCache(cm, com.eshipper.domain.EcomStore.class.getName() + ".ecomMailTemplates");
            createCache(cm, com.eshipper.domain.EcomStore.class.getName() + ".ecomOrders");
            createCache(cm, com.eshipper.domain.EcomStore.class.getName() + ".shipmentServices");
            createCache(cm, com.eshipper.domain.EcomStoreAddress.class.getName());
            createCache(cm, com.eshipper.domain.EcomStoreColorTheme.class.getName());
            createCache(cm, com.eshipper.domain.ShipmentService.class.getName());
            createCache(cm, com.eshipper.domain.ShipmentService.class.getName() + ".ecomStores");
            createCache(cm, com.eshipper.domain.EcomStoreShipmentSettings.class.getName());
            createCache(cm, com.eshipper.domain.EcomStorePackageSettings.class.getName());
            createCache(cm, com.eshipper.domain.EcomStoreMarkup.class.getName());
            createCache(cm, com.eshipper.domain.EcomMarkupPrimary.class.getName());
            createCache(cm, com.eshipper.domain.EcomMarkupSecondary.class.getName());
            createCache(cm, com.eshipper.domain.EcomMarkupTertiary.class.getName());
            createCache(cm, com.eshipper.domain.EcomMarkupQuaternary.class.getName());
            createCache(cm, com.eshipper.domain.EcomMailTemplate.class.getName());
            createCache(cm, com.eshipper.domain.EcomProduct.class.getName());
            createCache(cm, com.eshipper.domain.EcomProduct.class.getName() + ".ecomProductImages");
            createCache(cm, com.eshipper.domain.EcomProduct.class.getName() + ".ecomWarehouses");
            createCache(cm, com.eshipper.domain.EcomProductImage.class.getName());
            createCache(cm, com.eshipper.domain.EcomWarehouse.class.getName());
            createCache(cm, com.eshipper.domain.EcomWarehouse.class.getName() + ".ecomProducts");
            createCache(cm, com.eshipper.domain.EcomOrder.class.getName());
            createCache(cm, com.eshipper.domain.EcomOrder.class.getName() + ".ecomProducts");
            createCache(cm, com.eshipper.domain.Currency.class.getName());
            createCache(cm, com.eshipper.domain.Company.class.getName());
            createCache(cm, com.eshipper.domain.Country.class.getName());
            createCache(cm, com.eshipper.domain.ShippingAddress.class.getName());
            createCache(cm, com.eshipper.domain.Province.class.getName());
            createCache(cm, com.eshipper.domain.City.class.getName());
            createCache(cm, com.eshipper.domain.EcomStoreSync.class.getName());
            createCache(cm, com.eshipper.domain.WoSalesAgent.class.getName());
            createCache(cm, com.eshipper.domain.SalesAgentType.class.getName());
            createCache(cm, com.eshipper.domain.WoSalesAgentDetails.class.getName());
            createCache(cm, com.eshipper.domain.PaymentMethod.class.getName());
            createCache(cm, com.eshipper.domain.WoSalesCommissionDetails.class.getName());
            createCache(cm, com.eshipper.domain.WoSalesCommissionDetails.class.getName() + ".woSalesCommissionCarriers");
            createCache(cm, com.eshipper.domain.WoSalesCommissionCarrier.class.getName());
            createCache(cm, com.eshipper.domain.WoSalesOperationalDetails.class.getName());
            createCache(cm, com.eshipper.domain.WoSalesOperationalDetails.class.getName() + ".woSalesOperationalCarriers");
            createCache(cm, com.eshipper.domain.WoSalesOperationalCarrier.class.getName());
            createCache(cm, com.eshipper.domain.WoSalesCommissionTransfer.class.getName());
            createCache(cm, com.eshipper.domain.Carrier.class.getName());
            createCache(cm, com.eshipper.domain.CarrierService.class.getName());
            createCache(cm, com.eshipper.domain.User10.class.getName());
            createCache(cm, com.eshipper.domain.ElasticSearchWoSalesAgent.class.getName());
            createCache(cm, com.eshipper.domain.ElasticStatus.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }

}
