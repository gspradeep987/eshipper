package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.EcomStorePackageSettings} entity.
 */
public class EcomStorePackageSettingsDTO implements Serializable {

    private Long id;

    private Boolean packingInfo1;

    private Boolean packingInfo2;

    private Boolean packingInfo3;

    private Boolean packingInfo4;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isPackingInfo1() {
        return packingInfo1;
    }

    public void setPackingInfo1(Boolean packingInfo1) {
        this.packingInfo1 = packingInfo1;
    }

    public Boolean isPackingInfo2() {
        return packingInfo2;
    }

    public void setPackingInfo2(Boolean packingInfo2) {
        this.packingInfo2 = packingInfo2;
    }

    public Boolean isPackingInfo3() {
        return packingInfo3;
    }

    public void setPackingInfo3(Boolean packingInfo3) {
        this.packingInfo3 = packingInfo3;
    }

    public Boolean isPackingInfo4() {
        return packingInfo4;
    }

    public void setPackingInfo4(Boolean packingInfo4) {
        this.packingInfo4 = packingInfo4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO = (EcomStorePackageSettingsDTO) o;
        if (ecomStorePackageSettingsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ecomStorePackageSettingsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EcomStorePackageSettingsDTO{" +
            "id=" + getId() +
            ", packingInfo1='" + isPackingInfo1() + "'" +
            ", packingInfo2='" + isPackingInfo2() + "'" +
            ", packingInfo3='" + isPackingInfo3() + "'" +
            ", packingInfo4='" + isPackingInfo4() + "'" +
            "}";
    }
}
