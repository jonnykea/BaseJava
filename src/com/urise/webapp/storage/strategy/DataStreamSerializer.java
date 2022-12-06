package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializerStrategy {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeCollection(dos, contacts.entrySet(), entryContacts -> {
                dos.writeUTF(entryContacts.getKey().name());
                dos.writeUTF(entryContacts.getValue());
            });
            writeCollection(dos, r.getSections().entrySet(), entrySections -> {
                SectionType type = entrySections.getKey();
                Section section = entrySections.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL, OBJECTIVE -> {
                        dos.writeUTF(((TextSection) section).getContent());
                    }
                    case ACHIEVEMENT, QUALIFICATIONS -> writeCollection(dos, ((ListSection) section).getItems(), EntryString ->
                            dos.writeUTF(EntryString));
                    case EXPERIENCE, EDUCATION -> writeCollection(dos, ((CompanySection) section).getCompanies(), entryCompanies ->
                            {
                                dos.writeUTF(entryCompanies.getName());
                                dos.writeUTF(entryCompanies.getWebsite());
                                writeCollection(dos, entryCompanies.getPeriods(), entryPeriods ->
                                {
                                    writeLocalDate(dos, entryPeriods.getStartDate());
                                    writeLocalDate(dos, entryPeriods.getEndDate());
                                    dos.writeUTF(entryPeriods.getPosition());
                                    dos.writeUTF(entryPeriods.getDescription());
                                });
                            }
                    );
                }
            });

        }
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, EntryElement<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private interface EntryElement<T> {
        void write(T t) throws IOException;
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
        dos.writeInt(ld.getDayOfMonth());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readItems(dis, () ->
                    resume.setContacts(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readItems(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.setSections(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    private void readItems(DataInputStream dis, ElementToRead processor) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            processor.readItem();
        }
    }

    private interface ElementToRead {
        void readItem() throws IOException;
    }

    private Section readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return new ListSection(readList(dis, () -> dis.readUTF()));
            case EXPERIENCE:
            case EDUCATION:
                return new CompanySection(readList(dis, () -> readCompany(dis)));

            default:
                throw new IllegalStateException();
        }
    }

    private <T> List<T> readList(DataInputStream dis, ElementToEntry<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private interface ElementToEntry<T> {
        T read() throws IOException;

    }

    private Company readCompany(DataInputStream dis) throws IOException {
        return new Company(dis.readUTF(), dis.readUTF(), readList(dis, () ->
                new Period(readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF())));
    }
}