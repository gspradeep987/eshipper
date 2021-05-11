package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.SisyphusJob} entity.
 */
public class SisyphusJobDTO implements Serializable {

  private Long id;

  private String name;

  private String scheduleMinute;

  private String scheduleHour;

  private String scheduleDay;

  private String scheduleMonth;

  private String shouldrunYN;

  private Long retries;

  private Boolean moniterScheduleYN;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getScheduleMinute() {
    return scheduleMinute;
  }

  public void setScheduleMinute(String scheduleMinute) {
    this.scheduleMinute = scheduleMinute;
  }

  public String getScheduleHour() {
    return scheduleHour;
  }

  public void setScheduleHour(String scheduleHour) {
    this.scheduleHour = scheduleHour;
  }

  public String getScheduleDay() {
    return scheduleDay;
  }

  public void setScheduleDay(String scheduleDay) {
    this.scheduleDay = scheduleDay;
  }

  public String getScheduleMonth() {
    return scheduleMonth;
  }

  public void setScheduleMonth(String scheduleMonth) {
    this.scheduleMonth = scheduleMonth;
  }

  public String getShouldrunYN() {
    return shouldrunYN;
  }

  public void setShouldrunYN(String shouldrunYN) {
    this.shouldrunYN = shouldrunYN;
  }

  public Long getRetries() {
    return retries;
  }

  public void setRetries(Long retries) {
    this.retries = retries;
  }

  public Boolean getMoniterScheduleYN() {
    return moniterScheduleYN;
  }

  public void setMoniterScheduleYN(Boolean moniterScheduleYN) {
    this.moniterScheduleYN = moniterScheduleYN;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SisyphusJobDTO)) {
      return false;
    }

    SisyphusJobDTO sisyphusJobDTO = (SisyphusJobDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, sisyphusJobDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "SisyphusJobDTO{" +
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
