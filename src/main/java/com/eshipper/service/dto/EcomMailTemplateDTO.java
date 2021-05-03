package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.eshipper.domain.EcomMailTemplate} entity.
 */
public class EcomMailTemplateDTO implements Serializable {

  private Long id;

  @Size(max = 50)
  private String templateName;

  @Size(max = 50)
  private String templateType;

  @Size(max = 50)
  private String note;

  @Size(max = 50)
  private String subject;

  @Size(max = 50)
  private String content;

  private Boolean isCustomTemplate;

  private Boolean isOrder;

  private Boolean isShipment;

  private Boolean isProductPurchased;

  private Boolean isAmountPaid;

  private Boolean isStoreInfo;

  private Boolean isBody1;

  private EcomStoreDTO ecomStore;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTemplateName() {
    return templateName;
  }

  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }

  public String getTemplateType() {
    return templateType;
  }

  public void setTemplateType(String templateType) {
    this.templateType = templateType;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Boolean getIsCustomTemplate() {
    return isCustomTemplate;
  }

  public void setIsCustomTemplate(Boolean isCustomTemplate) {
    this.isCustomTemplate = isCustomTemplate;
  }

  public Boolean getIsOrder() {
    return isOrder;
  }

  public void setIsOrder(Boolean isOrder) {
    this.isOrder = isOrder;
  }

  public Boolean getIsShipment() {
    return isShipment;
  }

  public void setIsShipment(Boolean isShipment) {
    this.isShipment = isShipment;
  }

  public Boolean getIsProductPurchased() {
    return isProductPurchased;
  }

  public void setIsProductPurchased(Boolean isProductPurchased) {
    this.isProductPurchased = isProductPurchased;
  }

  public Boolean getIsAmountPaid() {
    return isAmountPaid;
  }

  public void setIsAmountPaid(Boolean isAmountPaid) {
    this.isAmountPaid = isAmountPaid;
  }

  public Boolean getIsStoreInfo() {
    return isStoreInfo;
  }

  public void setIsStoreInfo(Boolean isStoreInfo) {
    this.isStoreInfo = isStoreInfo;
  }

  public Boolean getIsBody1() {
    return isBody1;
  }

  public void setIsBody1(Boolean isBody1) {
    this.isBody1 = isBody1;
  }

  public EcomStoreDTO getEcomStore() {
    return ecomStore;
  }

  public void setEcomStore(EcomStoreDTO ecomStore) {
    this.ecomStore = ecomStore;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomMailTemplateDTO)) {
      return false;
    }

    EcomMailTemplateDTO ecomMailTemplateDTO = (EcomMailTemplateDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, ecomMailTemplateDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomMailTemplateDTO{" +
            "id=" + getId() +
            ", templateName='" + getTemplateName() + "'" +
            ", templateType='" + getTemplateType() + "'" +
            ", note='" + getNote() + "'" +
            ", subject='" + getSubject() + "'" +
            ", content='" + getContent() + "'" +
            ", isCustomTemplate='" + getIsCustomTemplate() + "'" +
            ", isOrder='" + getIsOrder() + "'" +
            ", isShipment='" + getIsShipment() + "'" +
            ", isProductPurchased='" + getIsProductPurchased() + "'" +
            ", isAmountPaid='" + getIsAmountPaid() + "'" +
            ", isStoreInfo='" + getIsStoreInfo() + "'" +
            ", isBody1='" + getIsBody1() + "'" +
            ", ecomStore=" + getEcomStore() +
            "}";
    }
}
