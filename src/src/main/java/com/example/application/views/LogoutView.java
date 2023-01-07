package com.example.application.views;

import com.example.application.data.constants.Notifications;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;

@PageTitle("Logout")
public class LogoutView extends Composite<VerticalLayout> {
    public LogoutView() {
        UI.getCurrent().getPage().setLocation("login");
        VaadinSession.getCurrent().getSession().invalidate();
        VaadinSession.getCurrent().close();

        RouteConfiguration.forApplicationScope().setRoute("", LoginView.class);
        RouteConfiguration.forApplicationScope().setRoute("login", LoginView.class);
        RouteConfiguration.forApplicationScope().setRoute("register", RegisterView.class);

        Notifications.GenerateSuccessNotification(Notifications.SUCCESSFUL_LOGOUT_MESSAGE);
    }
}