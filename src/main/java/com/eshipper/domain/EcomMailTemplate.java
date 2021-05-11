package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EcomMailTemplate.
 */
@Entity
@Table(name = "ecom_mail_template")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EcomMailTemplate implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 50)
  @Column(name = "template_name", length = 50)
  private String templateName;

  @Size(max = 50)
  @Column(name = "template_type", length = 50)
  private String templateType;

  @Size(max = 50)
  @Column(name = "note", length = 50)
  private String note;

  @Size(max = 50)
  @Column(name = "subject", length = 50)
  private String subject;

  @Size(max = 50)
  @Column(name = "content", length = 50)
  private String content;

  @Column(name = "is_custom_template")
  private Boolean isCustomTemplate;

  @Column(name = "is_order")
  private Boolean isOrder;

  @Column(name = "is_shipment")
  private Boolean isShipment;

  @Column(name = "is_product_purchased")
  private Boolean isProductPurchased;

  @Column(name = "is_amount_paid")
  private Boolean isAmountPaid;

  @Column(name = "is_store_info")
  private Boolean isStoreInfo;

  @Column(name = "is_body_1")
  private Boolean isBody1;

  @ManyToOne
  @JsonIgnoreProperties(
    value = {
      "ecomStoreAddress",
      "ecomStoreColorTheme",
      "ecomStoreShipmentSettings",
      "ecomStorePackageSettings",
      "ecomStoreMarkup",
      "ecomMailTemplates",
      "ecomOrders",
      "company",
      "user",
      "ecomProduct",
      "shipmentServices",
    },
    allowSetters = true
  )
  private EcomStore ecomStore;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EcomMailTemplate id(Long id) {
    this.id = id;
    return this;
  }

  public String getTemplateName() {
    return this.templateName;
  }

  public EcomMailTemplate templateName(String templateName) {
    this.templateName = templateName;
    return this;
  }

  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }

  public String getTemplateType() {
    return this.templateType;
  }

  public EcomMailTemplate templateType(String templateType) {
    this.templateType = templateType;
    return this;
  }

  public void setTemplateType(String templateType) {
    this.templateType = templateType;
  }

  public String getNote() {
    return this.note;
  }

  public EcomMailTemplate note(String note) {
    this.note = note;
    return this;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getSubject() {
    return this.subject;
  }

  public EcomMailTemplate subject(String subject) {
    this.subject = subject;
    return this;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getContent() {
    return this.content;
  }

  public EcomMailTemplate content(String content) {
    this.content = content;
    return this;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Boolean getIsCustomTemplate() {
    return this.isCustomTemplate;
  }

  public EcomMailTemplate isCustomTemplate(Boolean isCustomTemplate) {
    this.isCustomTemplate = isCustomTemplate;
    return this;
  }

  public void setIsCustomTemplate(Boolean isCustomTemplate) {
    this.isCustomTemplate = isCustomTemplate;
  }

  public Boolean getIsOrder() {
    return this.isOrder;
  }

  public EcomMailTemplate isOrder(Boolean isOrder) {
    this.isOrder = isOrder;
    return this;
  }

  public void setIsOrder(Boolean isOrder) {
    this.isOrder = isOrder;
  }

  public Boolean getIsShipment() {
    return this.isShipment;
  }

  public EcomMailTemplate isShipment(Boolean isShipment) {
    this.isShipment = isShipment;
    return this;
  }

  public void setIsShipment(Boolean isShipment) {
    this.isShipment = isShipment;
  }

  public Boolean getIsProductPurchased() {
    return this.isProductPurchased;
  }

  public EcomMailTemplate isProductPurchased(Boolean isProductPurchased) {
    this.isProductPurchased = isProductPurchased;
    return this;
  }

  public void setIsProductPurchased(Boolean isProductPurchased) {
    this.isProductPurchased = isProductPurchased;
  }

  public Boolean getIsAmountPaid() {
    return this.isAmountPaid;
  }

  public EcomMailTemplate isAmountPaid(Boolean isAmountPaid) {
    this.isAmountPaid = isAmountPaid;
    return this;
  }

  public void setIsAmountPaid(Boolean isAmountPaid) {
    this.isAmountPaid = isAmountPaid;
  }

  public Boolean getIsStoreInfo() {
    return this.isStoreInfo;
  }

  public EcomMailTemplate isStoreInfo(Boolean isStoreInfo) {
    this.isStoreInfo = isStoreInfo;
    return this;
  }

  public void setIsStoreInfo(Boolean isStoreInfo) {
    this.isStoreInfo = isStoreInfo;
  }

  public Boolean getIsBody1() {
    return this.isBody1;
  }

  public EcomMailTemplate isBody1(Boolean isBody1) {
    this.isBody1 = isBody1;
    return this;
  }

  public void setIsBody1(Boolean isBody1) {
    this.isBody1 = isBody1;
  }

  public EcomStore getEcomStore() {
    return this.ecomStore;
  }

  public EcomMailTemplate ecomStore(EcomStore ecomStore) {
    this.setEcomStore(ecomStore);
    return this;
  }

  public void setEcomStore(EcomStore ecomStore) {
    this.ecomStore = ecomStore;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EcomMailTemplate)) {
      return false;
    }
    return id != null && id.equals(((EcomMailTemplate) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "EcomMailTemplate{" +
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
            "}";
    }
}
