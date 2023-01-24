package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.*;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.util.DateUtil;
import com.urise.webapp.util.CheckInNull;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage;// = Config.get().getStorage();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume r = storage.get(uuid);
        r.setFullName(fullName);
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (!CheckInNull.isEmpty(value)) {
                r.setContact(type, value);
            } else {
                if (CheckInNull.isEmpty(value)) {
                    r.getContacts().remove(type);
                } else {
                    r.setContact(type, value);
                }
            }
        }
            for (SectionType type : SectionType.values()) {
                String value = request.getParameter(type.name());
                String[] values = request.getParameterValues(type.name());
                if (CheckInNull.isEmpty(value) && values.length < 2) {
                    r.getSections().remove(type);
                } else {
                    switch (type) {
                        case OBJECTIVE:
                        case PERSONAL:
                            r.setSection(type, new TextSection(value));
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            r.setSection(type, new ListSection(value.split("\\n")));
                            break;
                        case EDUCATION:
                        case EXPERIENCE:
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
                            break;
                    }
                }
            }
            storage.update(r);
            response.sendRedirect("resume");
        }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
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
            case "edit" -> {
                r = storage.get(uuid);
                for (SectionType type : new SectionType[]{SectionType.EXPERIENCE, SectionType.EDUCATION}) {
                    CompanySection section = (CompanySection) r.getSection(type);
                    List<Company> emptyFirstCompany = new ArrayList<>();
                    emptyFirstCompany.add(Company.EMPTY);
                    if (section != null) {
                        for (Company company : section.getCompanies()) {
                            List<Period> emptyFirstPeriods = new ArrayList<>();
                            emptyFirstPeriods.add(Period.EMPTY);
                            emptyFirstPeriods.addAll(company.getPeriods());
                            emptyFirstCompany.add(new Company(company.getName(), company.getWebsite(), emptyFirstPeriods));
                        }
                    }
                    r.setSection(type, new CompanySection(emptyFirstCompany));
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

