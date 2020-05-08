package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A EcomorderAttachment.
 */
@Entity
@Table(name = "ecomorder_attachment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EcomorderAttachment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @Size(max = 255)
    @Column(name = "attachment_path", length = 255)
    private String attachmentPath;

    @ManyToOne
    @JsonIgnoreProperties("ecomorderAttachments")
    private EcomOrder ecomOrder;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public EcomorderAttachment name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public EcomorderAttachment attachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
        return this;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public EcomOrder getEcomOrder() {
        return ecomOrder;
    }

    public EcomorderAttachment ecomOrder(EcomOrder ecomOrder) {
        this.ecomOrder = ecomOrder;
        return this;
    }

    public void setEcomOrder(EcomOrder ecomOrder) {
        this.ecomOrder = ecomOrder;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EcomorderAttachment)) {
            return false;
        }
        return id != null && id.equals(((EcomorderAttachment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EcomorderAttachment{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", attachmentPath='" + getAttachmentPath() + "'" +
            "}";
    }
}
