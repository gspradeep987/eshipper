package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A EcomStorePackageSettings.
 */
@Entity
@Table(name = "ecom_store_package_settings")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EcomStorePackageSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "packing_info_1")
    private Boolean packingInfo1;

    @Column(name = "packing_info_2")
    private Boolean packingInfo2;

    @Column(name = "packing_info_3")
    private Boolean packingInfo3;

    @Column(name = "packing_info_4")
    private Boolean packingInfo4;

    @OneToOne(mappedBy = "ecomStorePackageSettings")
    @JsonIgnore
    private EcomStore ecomStore;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isPackingInfo1() {
        return packingInfo1;
    }

    public EcomStorePackageSettings packingInfo1(Boolean packingInfo1) {
        this.packingInfo1 = packingInfo1;
        return this;
    }

    public void setPackingInfo1(Boolean packingInfo1) {
        this.packingInfo1 = packingInfo1;
    }

    public Boolean isPackingInfo2() {
        return packingInfo2;
    }

    public EcomStorePackageSettings packingInfo2(Boolean packingInfo2) {
        this.packingInfo2 = packingInfo2;
        return this;
    }

    public void setPackingInfo2(Boolean packingInfo2) {
        this.packingInfo2 = packingInfo2;
    }

    public Boolean isPackingInfo3() {
        return packingInfo3;
    }

    public EcomStorePackageSettings packingInfo3(Boolean packingInfo3) {
        this.packingInfo3 = packingInfo3;
        return this;
    }

    public void setPackingInfo3(Boolean packingInfo3) {
        this.packingInfo3 = packingInfo3;
    }

    public Boolean isPackingInfo4() {
        return packingInfo4;
    }

    public EcomStorePackageSettings packingInfo4(Boolean packingInfo4) {
        this.packingInfo4 = packingInfo4;
        return this;
    }

    public void setPackingInfo4(Boolean packingInfo4) {
        this.packingInfo4 = packingInfo4;
    }

    public EcomStore getEcomStore() {
        return ecomStore;
    }

    public EcomStorePackageSettings ecomStore(EcomStore ecomStore) {
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
        if (!(o instanceof EcomStorePackageSettings)) {
            return false;
        }
        return id != null && id.equals(((EcomStorePackageSettings) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EcomStorePackageSettings{" +
            "id=" + getId() +
            ", packingInfo1='" + isPackingInfo1() + "'" +
            ", packingInfo2='" + isPackingInfo2() + "'" +
            ", packingInfo3='" + isPackingInfo3() + "'" +
            ", packingInfo4='" + isPackingInfo4() + "'" +
            "}";
    }
}
