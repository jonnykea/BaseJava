package com.urise.webapp.model;

import java.util.Map;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

/**
 * Initial resume class
 */
public class Resume extends Section implements Comparable<Resume>{
    // Unique identifier
    private final String uuid;
    private String fullName;
    private Map<SectionType, Section> sections;
    private Map<ContactType, String> contacts;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        requireNonNull(uuid, "uuid must not be null");
        requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume(String uuid, String fullName, Map<SectionType, Section> sections, Map<ContactType, String> contacts) {
        requireNonNull(uuid, "uuid must not be null");
        requireNonNull(fullName, "fullName must not be null");
        requireNonNull(sections, "sections must not be null");
        requireNonNull(contacts, "contacts must not be null");
        this.sections = sections;
        this.contacts = contacts;
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        if (!fullName.equals(resume.fullName)) return false;
        if (!sections.equals(resume.sections)) return false;
        return contacts.equals(resume.contacts);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + sections.hashCode();
        result = 31 * result + contacts.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
    }

    @Override
    public int compareTo(Resume o) {
        int result = fullName.compareTo(o.fullName);
        return result != 0 ? result : uuid.compareTo(o.uuid);
    }
}