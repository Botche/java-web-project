package com.example.application.views;

import com.example.application.data.constants.GlobalConstants;
import com.example.application.data.entity.Roles;
import com.example.application.data.entity.Users;
import com.example.application.data.service.AuthService;
import com.example.application.views.list.ListView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    private final AuthService authService;

    public MainLayout(AuthService authService) {
        this.authService = authService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1(GlobalConstants.COMPANY_NAME);
        logo.addClassNames("text-l", "m-m");

        Button logoutButton = new Button("Log out", e -> UI.getCurrent().navigate("/logout"));

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logoutButton);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);

    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("List", ListView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        VerticalLayout sidebar = new VerticalLayout(
                listLink
        );

        Users authenticatedUser = authService.getAuthenticatedUser();
        if (authenticatedUser.getRoleId() == Roles.ADMIN) {
            sidebar.add(new RouterLink("Dashboard", DashboardView.class));
        }

        addToDrawer(sidebar);
    }
}
