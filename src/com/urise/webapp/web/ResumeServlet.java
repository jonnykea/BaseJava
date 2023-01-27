package com.urise.webapp.web;

import com.urise.webapp.model.*;
import com.urise.webapp.storage.SqlStorage;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.util.CheckInNull;
import com.urise.webapp.util.DateUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private final Storage storage = new SqlStorage("jdbc:postgresql://localhost:5432/resumes", "postgres", "SQL1525");


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        final boolean isCreate = (uuid == null || uuid.length() == 0);
        Resume r;
        if (isCreate) {
            r = new Resume(fullName);
        } else {
            r = storage.get(uuid);
            r.setFullName(fullName);
        }
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (CheckInNull.isEmpty(value)) {
                r.getContacts().remove(type);
            } else {
                r.setContact(type, value);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            if (CheckInNull.isEmpty(value) && values.length < 2) {
                r.getSections().remove(type);
            } else {
                switch (type) {
                    case OBJECTIVE, PERSONAL -> r.setSection(type, new TextSection(value.trim()));
                    case ACHIEVEMENT, QUALIFICATIONS -> r.setSection(type, new ListSection(value.split("\\n")));
                    case EDUCATION, EXPERIENCE -> {
                        List<Company> companies = new ArrayList<>();
                        String[] urls = request.getParameterValues(type.name() + "url");
                        for (int i = 0; i < values.length; i++) {
                            String name = values[i];
                            if (!CheckInNull.isEmpty(name)) {
                                List<Period> periods = new ArrayList<>();
                                String pfx = type.name() + i;
                                String[] startDates = request.getParameterValues(pfx + "startDate");
                                String[] endDates = request.getParameterValues(pfx + "endDate");
                                String[] titles = request.getParameterValues(pfx + "title");
                                String[] descriptions = request.getParameterValues(pfx + "description");
                                for (int j = 0; j < titles.length; j++) {
                                    if (!CheckInNull.isEmpty(titles[j])) {
                                        periods.add(new Period(DateUtil.parse(startDates[j]), DateUtil.parse(endDates[j]), titles[j], descriptions[j]));
                                    }
                                }
                                companies.add(new Company(name, urls[i], periods));
                            }
                        }
                        r.setSection(type, new CompanySection(companies));
                    }
                }
            }
        }
        if (isCreate) {
            storage.save(r);
        } else {
            storage.update(r);
        }
        response.sendRedirect("resume");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete" -> {
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            }
            case "view" -> r = storage.get(uuid);
            case "add" -> r = Resume.EMPTY;
            case "edit" -> {
                r = storage.get(uuid);
                for (SectionType type : SectionType.values()) {
                    Section section = r.getSection(type);
                    switch (type) {
                        case OBJECTIVE, PERSONAL -> {
                            if (section == null) {
                                section = TextSection.EMPTY;
                            }
                        }
                        case ACHIEVEMENT, QUALIFICATIONS -> {
                            if (section == null) {
                                section = ListSection.EMPTY;
                            }
                        }
                        case EXPERIENCE, EDUCATION -> {
                            CompanySection companySection = (CompanySection) section;
                            List<Company> emptyFirstCompany = new ArrayList<>();
                            emptyFirstCompany.add(Company.EMPTY);
                            if (companySection != null) {
                                for (Company company : companySection.getCompanies()) {
                                    List<Period> emptyFirstPeriods = new ArrayList<>();
                                    emptyFirstPeriods.add(Period.EMPTY);
                                    emptyFirstPeriods.addAll(company.getPeriods());
                                    emptyFirstCompany.add(new Company(company.getName(), company.getWebsite(), emptyFirstPeriods));
                                }
                            }
                            section = new CompanySection(emptyFirstCompany);
                        }
                    }
                    r.setSection(type, section);
                }
            }
            default -> throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}

