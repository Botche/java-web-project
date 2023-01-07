package com.example.application.views;

import com.example.application.data.constants.GlobalConstants;
import com.example.application.data.constants.Notifications;
import com.example.application.data.service.AuthService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@RouteAlias(value = "")
@PageTitle(GlobalConstants.LOGIN_PAGE_TITLE)
@AnonymousAllowed
public class LoginView extends VerticalLayout {
	private final AuthService authService;

	public LoginView(AuthService authService) {
		this.authService = authService;

		configLoginForm();
	}

	private void configLoginForm() {
		addClassName("login-view");
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);

		TextField username = new TextField("Username");
		PasswordField password = new PasswordField("Password");

		H1 formHeader = new H1(GlobalConstants.COMPANY_NAME);
		RouterLink registerLink = new RouterLink("Register", RegisterView.class);

		add(
			formHeader,
			new H2("Login"),
			username,
			password,
			new Button("Send", event -> {
				try {
					login(
						username.getValue(),
						password.getValue()
					);
				} catch (Exception e) {
					Notifications.GenerateErrorNotification(Notifications.USERNAME_PASSWORD_WRONG_MESSAGE);
				}
			}),
			registerLink
		);
	}

	private void login(String username, String password) throws AuthService.AuthException {
		if (username.trim().isEmpty()) {
			Notifications.GenerateErrorNotification(Notifications.ENTER_USERNAME_MESSAGE);
		} else if (password.isEmpty()) {
			Notifications.GenerateErrorNotification(Notifications.ENTER_PASSWORD_MESSAGE);
		} else {
			authService.authenticate(username, password);
			UI.getCurrent().navigate("/contacts");

			Notifications.GenerateSuccessNotification(Notifications.SUCCESSFUL_LOGIN_MESSAGE);
		}
	}
}