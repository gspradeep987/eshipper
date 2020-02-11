package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EcomStoreAddress.
 */
@Entity
@Table(name = "ecom_store_address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EcomStoreAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "address_1", length = 255)
    private String address1;

    @Size(max = 255)
    @Column(name = "address_2", length = 255)
    private String address2;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @Size(max = 25)
    @Column(name = "phone", length = 25)
    private String phone;

    @Size(max = 255)
    @Column(name = "email_acc_id", length = 255)
    private String emailAccId;

    @Column(name = "default_address")
    private Boolean defaultAddress;

    @ManyToOne
    @JsonIgnoreProperties("ecomStoreAddresses")
    private Country country;

    @ManyToOne
    @JsonIgnoreProperties("ecomStoreAddresses")
    private Province province;

    @ManyToOne
    @JsonIgnoreProperties("ecomStoreAddresses")
    private City city;

    @OneToOne(mappedBy = "ecomStoreAddress")
    @JsonIgnore
    private EcomStore ecomStore;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public EcomStoreAddress address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public EcomStoreAddress address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getName() {
        return name;
    }

    public EcomStoreAddress name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public EcomStoreAddress phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailAccId() {
        return emailAccId;
    }

    public EcomStoreAddress emailAccId(String emailAccId) {
        this.emailAccId = emailAccId;
        return this;
    }

    public void setEmailAccId(String emailAccId) {
        this.emailAccId = emailAccId;
    }

    public Boolean isDefaultAddress() {
        return defaultAddress;
    }

    public EcomStoreAddress defaultAddress(Boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
        return this;
    }

    public void setDefaultAddress(Boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public Country getCountry() {
        return country;
    }

    public EcomStoreAddress country(Country country) {
        this.country = country;
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Province getProvince() {
        return province;
    }

    public EcomStoreAddress province(Province province) {
        this.province = province;
        return this;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public City getCity() {
        return city;
    }

    public EcomStoreAddress city(City city) {
        this.city = city;
        return this;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public EcomStore getEcomStore() {
        return ecomStore;
    }

    public EcomStoreAddress ecomStore(EcomStore ecomStore) {
        this.ecomStore = ecomStore;
        return this;
    }

    public void setEcomStore(EcomStore ecomStore) {
        this.ecomStore = ecomStore;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EcomStoreAddress)) {
            return false;
        }
        return id != null && id.equals(((EcomStoreAddress) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EcomStoreAddress{" +
            "id=" + getId() +
            ", address1='" + getAddress1() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", name='" + getName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", emailAccId='" + getEmailAccId() + "'" +
            ", defaultAddress='" + isDefaultAddress() + "'" +
            "}";
    }
}
