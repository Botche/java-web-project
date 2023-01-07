package com.example.application.views;

import com.example.application.data.constants.GlobalConstants;
import com.example.application.data.service.CompaniesService;
import com.example.application.data.service.ContactsService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle(GlobalConstants.DASHBOARD_PAGE_TITLE)
public class DashboardView extends VerticalLayout {

    private ContactsService contactsService;
    private CompaniesService companiesService;

    public DashboardView(ContactsService contactsService, CompaniesService companiesService) {
        this.contactsService = contactsService;
        this.companiesService = companiesService;

        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER); 
        add(getContactStats(), getCompaniesChart());
    }

    private Component getContactStats() {
        Span stats = new Span(contactsService.countContacts() + " contacts");
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }

    private Chart getCompaniesChart() {
        Chart chart = new Chart(ChartType.PIE);

        DataSeries dataSeries = new DataSeries();
        companiesService.findAllCompanies().forEach(company ->
            dataSeries.add(new DataSeriesItem(company.getName(), company.getEmployeeCount()))); 
        chart.getConfiguration().setSeries(dataSeries);

        return chart;
    }
}