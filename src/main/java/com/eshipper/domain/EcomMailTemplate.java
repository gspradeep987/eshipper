package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EcomMailTemplate.
 */
@Entity
@Table(name = "ecom_mail_template")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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
    @JsonIgnoreProperties("ecomMailTemplates")
    private EcomStore ecomStore;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public EcomMailTemplate templateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateType() {
        return templateType;
    }

    public EcomMailTemplate templateType(String templateType) {
        this.templateType = templateType;
        return this;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getNote() {
        return note;
    }

    public EcomMailTemplate note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSubject() {
        return subject;
    }

    public EcomMailTemplate subject(String subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public EcomMailTemplate content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean isIsCustomTemplate() {
        return isCustomTemplate;
    }

    public EcomMailTemplate isCustomTemplate(Boolean isCustomTemplate) {
        this.isCustomTemplate = isCustomTemplate;
        return this;
    }

    public void setIsCustomTemplate(Boolean isCustomTemplate) {
        this.isCustomTemplate = isCustomTemplate;
    }

    public Boolean isIsOrder() {
        return isOrder;
    }

    public EcomMailTemplate isOrder(Boolean isOrder) {
        this.isOrder = isOrder;
        return this;
    }

    public void setIsOrder(Boolean isOrder) {
        this.isOrder = isOrder;
    }

    public Boolean isIsShipment() {
        return isShipment;
    }

    public EcomMailTemplate isShipment(Boolean isShipment) {
        this.isShipment = isShipment;
        return this;
    }

    public void setIsShipment(Boolean isShipment) {
        this.isShipment = isShipment;
    }

    public Boolean isIsProductPurchased() {
        return isProductPurchased;
    }

    public EcomMailTemplate isProductPurchased(Boolean isProductPurchased) {
        this.isProductPurchased = isProductPurchased;
        return this;
    }

    public void setIsProductPurchased(Boolean isProductPurchased) {
        this.isProductPurchased = isProductPurchased;
    }

    public Boolean isIsAmountPaid() {
        return isAmountPaid;
    }

    public EcomMailTemplate isAmountPaid(Boolean isAmountPaid) {
        this.isAmountPaid = isAmountPaid;
        return this;
    }

    public void setIsAmountPaid(Boolean isAmountPaid) {
        this.isAmountPaid = isAmountPaid;
    }

    public Boolean isIsStoreInfo() {
        return isStoreInfo;
    }

    public EcomMailTemplate isStoreInfo(Boolean isStoreInfo) {
        this.isStoreInfo = isStoreInfo;
        return this;
    }

    public void setIsStoreInfo(Boolean isStoreInfo) {
        this.isStoreInfo = isStoreInfo;
    }

    public Boolean isIsBody1() {
        return isBody1;
    }

    public EcomMailTemplate isBody1(Boolean isBody1) {
        this.isBody1 = isBody1;
        return this;
    }

    public void setIsBody1(Boolean isBody1) {
        this.isBody1 = isBody1;
    }

    public EcomStore getEcomStore() {
        return ecomStore;
    }

    public EcomMailTemplate ecomStore(EcomStore ecomStore) {
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
        if (!(o instanceof EcomMailTemplate)) {
            return false;
        }
        return id != null && id.equals(((EcomMailTemplate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EcomMailTemplate{" +
            "id=" + getId() +
            ", templateName='" + getTemplateName() + "'" +
            ", templateType='" + getTemplateType() + "'" +
            ", note='" + getNote() + "'" +
            ", subject='" + getSubject() + "'" +
            ", content='" + getContent() + "'" +
            ", isCustomTemplate='" + isIsCustomTemplate() + "'" +
            ", isOrder='" + isIsOrder() + "'" +
            ", isShipment='" + isIsShipment() + "'" +
            ", isProductPurchased='" + isIsProductPurchased() + "'" +
            ", isAmountPaid='" + isIsAmountPaid() + "'" +
            ", isStoreInfo='" + isIsStoreInfo() + "'" +
            ", isBody1='" + isIsBody1() + "'" +
            "}";
    }
}
