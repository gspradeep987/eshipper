package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SisyphusJob.
 */
@Entity
@Table(name = "sisyphus_job")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SisyphusJob implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "schedule_minute")
  private String scheduleMinute;

  @Column(name = "schedule_hour")
  private String scheduleHour;

  @Column(name = "schedule_day")
  private String scheduleDay;

  @Column(name = "schedule_month")
  private String scheduleMonth;

  @Column(name = "shouldrun_yn")
  private String shouldrunYN;

  @Column(name = "retries")
  private Long retries;

  @Column(name = "moniter_schedule_yn")
  private Boolean moniterScheduleYN;

  @OneToMany(mappedBy = "sisyphusJob")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "sisyphusJob" }, allowSetters = true)
  private Set<SisyphusSubJob> subJobs = new HashSet<>();

  @OneToMany(mappedBy = "sisyphusJob")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "sisyphusJob" }, allowSetters = true)
  private Set<SisyphusJobParam> params = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SisyphusJob id(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return this.name;
  }

  public SisyphusJob name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getScheduleMinute() {
    return this.scheduleMinute;
  }

  public SisyphusJob scheduleMinute(String scheduleMinute) {
    this.scheduleMinute = scheduleMinute;
    return this;
  }

  public void setScheduleMinute(String scheduleMinute) {
    this.scheduleMinute = scheduleMinute;
  }

  public String getScheduleHour() {
    return this.scheduleHour;
  }

  public SisyphusJob scheduleHour(String scheduleHour) {
    this.scheduleHour = scheduleHour;
    return this;
  }

  public void setScheduleHour(String scheduleHour) {
    this.scheduleHour = scheduleHour;
  }

  public String getScheduleDay() {
    return this.scheduleDay;
  }

  public SisyphusJob scheduleDay(String scheduleDay) {
    this.scheduleDay = scheduleDay;
    return this;
  }

  public void setScheduleDay(String scheduleDay) {
    this.scheduleDay = scheduleDay;
  }

  public String getScheduleMonth() {
    return this.scheduleMonth;
  }

  public SisyphusJob scheduleMonth(String scheduleMonth) {
    this.scheduleMonth = scheduleMonth;
    return this;
  }

  public void setScheduleMonth(String scheduleMonth) {
    this.scheduleMonth = scheduleMonth;
  }

  public String getShouldrunYN() {
    return this.shouldrunYN;
  }

  public SisyphusJob shouldrunYN(String shouldrunYN) {
    this.shouldrunYN = shouldrunYN;
    return this;
  }

  public void setShouldrunYN(String shouldrunYN) {
    this.shouldrunYN = shouldrunYN;
  }

  public Long getRetries() {
    return this.retries;
  }

  public SisyphusJob retries(Long retries) {
    this.retries = retries;
    return this;
  }

  public void setRetries(Long retries) {
    this.retries = retries;
  }

  public Boolean getMoniterScheduleYN() {
    return this.moniterScheduleYN;
  }

  public SisyphusJob moniterScheduleYN(Boolean moniterScheduleYN) {
    this.moniterScheduleYN = moniterScheduleYN;
    return this;
  }

  public void setMoniterScheduleYN(Boolean moniterScheduleYN) {
    this.moniterScheduleYN = moniterScheduleYN;
  }

  public Set<SisyphusSubJob> getSubJobs() {
    return this.subJobs;
  }

  public SisyphusJob subJobs(Set<SisyphusSubJob> sisyphusSubJobs) {
    this.setSubJobs(sisyphusSubJobs);
    return this;
  }

  public SisyphusJob addSubJobs(SisyphusSubJob sisyphusSubJob) {
    this.subJobs.add(sisyphusSubJob);
    sisyphusSubJob.setSisyphusJob(this);
    return this;
  }

  public SisyphusJob removeSubJobs(SisyphusSubJob sisyphusSubJob) {
    this.subJobs.remove(sisyphusSubJob);
    sisyphusSubJob.setSisyphusJob(null);
    return this;
  }

  public void setSubJobs(Set<SisyphusSubJob> sisyphusSubJobs) {
    if (this.subJobs != null) {
      this.subJobs.forEach(i -> i.setSisyphusJob(null));
    }
    if (sisyphusSubJobs != null) {
      sisyphusSubJobs.forEach(i -> i.setSisyphusJob(this));
    }
    this.subJobs = sisyphusSubJobs;
  }

  public Set<SisyphusJobParam> getParams() {
    return this.params;
  }

  public SisyphusJob params(Set<SisyphusJobParam> sisyphusJobParams) {
    this.setParams(sisyphusJobParams);
    return this;
  }

  public SisyphusJob addParams(SisyphusJobParam sisyphusJobParam) {
    this.params.add(sisyphusJobParam);
    sisyphusJobParam.setSisyphusJob(this);
    return this;
  }

  public SisyphusJob removeParams(SisyphusJobParam sisyphusJobParam) {
    this.params.remove(sisyphusJobParam);
    sisyphusJobParam.setSisyphusJob(null);
    return this;
  }

  public void setParams(Set<SisyphusJobParam> sisyphusJobParams) {
    if (this.params != null) {
      this.params.forEach(i -> i.setSisyphusJob(null));
    }
    if (sisyphusJobParams != null) {
      sisyphusJobParams.forEach(i -> i.setSisyphusJob(this));
    }
    this.params = sisyphusJobParams;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SisyphusJob)) {
      return false;
    }
    return id != null && id.equals(((SisyphusJob) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "SisyphusJob{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", scheduleMinute='" + getScheduleMinute() + "'" +
            ", scheduleHour='" + getScheduleHour() + "'" +
            ", scheduleDay='" + getScheduleDay() + "'" +
            ", scheduleMonth='" + getScheduleMonth() + "'" +
            ", shouldrunYN='" + getShouldrunYN() + "'" +
            ", retries=" + getRetries() +
            ", moniterScheduleYN='" + getMoniterScheduleYN() + "'" +
            "}";
    }
}
