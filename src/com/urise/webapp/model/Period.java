package com.urise.webapp.model;

import com.urise.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

import static com.urise.webapp.util.DateUtil.NOW;
import static com.urise.webapp.util.DateUtil.of;
import static java.util.Objects.requireNonNull;

@XmlAccessorType(XmlAccessType.FIELD)
public class Period implements Serializable {
    private static final long serialVersionUID = 1L;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
    private String position;
    private String description;

    public Period() {
    }

    public Period(int startYear, Month startMonth, int dayOfMonth, String description) {
        this(of(startYear, startMonth, dayOfMonth), NOW, description);
    }

    public Period(int startYear, Month startMonth, int dayOfMonth, String position, String description) {
        this(of(startYear, startMonth, 1), NOW, position, description);
    }

    public Period(int startYear, Month startMonth, int startDayOfMonth, int endYear, Month endMonth,  int endDayOfMonth, String position, String description) {
        this(of(startYear, startMonth, startDayOfMonth), of(endYear, endMonth, endDayOfMonth), position, description);
    }

    public Period(int startYear, Month startMonth, int startDayOfMonth, int endYear, Month endMonth, int endDayOfMonth, String description) {
        this(of(startYear, startMonth, startDayOfMonth), of(endYear, endMonth, endDayOfMonth), description);
    }

    public Period(LocalDate startDate, LocalDate endDate, String description) {
        requireNonNull(startDate, "startDate must not be null");
        requireNonNull(endDate, "endDate must not be null");
        requireNonNull(description, "description must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public Period(LocalDate startDate, LocalDate endDate, String position, String description) {
        requireNonNull(startDate, "startDate must not be null");
        requireNonNull(endDate, "endDate must not be null");
        requireNonNull(description, "description must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getPosition() {
        return position;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return startDate + " - " + endDate + "\n" + (position == null ? "" : position + "\n") + description + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;

        if (!startDate.equals(period.startDate)) return false;
        if (!endDate.equals(period.endDate)) return false;
        if (!Objects.equals(position, period.position)) return false;
        return description.equals(period.description);
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + description.hashCode();
        return result;
    }
}