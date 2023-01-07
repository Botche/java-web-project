package com.example.application.views;

import com.example.application.data.constants.GlobalConstants;
import com.example.application.data.constants.Notifications;
import com.example.application.data.service.AuthService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("register")
@PageTitle(GlobalConstants.REGISTER_PAGE_TITLE)
@AnonymousAllowed
public class RegisterView extends VerticalLayout {
	private final AuthService authService;

	public RegisterView(AuthService authService) {
		this.authService = authService;

		configRegisterForm();
	}

	private void configRegisterForm() {
		addClassName("register-view");
		setSizeFull();
		setAlignItems(FlexComponent.Alignment.CENTER);
		setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

		TextField username = new TextField("Username");
		PasswordField password1 = new PasswordField("Password");
		PasswordField password2 = new PasswordField("Confirm password");

		H1 formHeader = new H1(GlobalConstants.COMPANY_NAME);
		RouterLink loginLink = new RouterLink("Login", LoginView.class);

		add(
			formHeader,
			new H2("Register"),
			username,
			password1,
			password2,
			new Button("Send", event -> register(
					username.getValue(),
					password1.getValue(),
					password2.getValue()
			)),
			loginLink
		);
	}

	private void register(String username, String password1, String password2) {
		if (username.trim().isEmpty()) {
			Notifications.GenerateErrorNotification(Notifications.ENTER_USERNAME_MESSAGE);
		} else if (password1.isEmpty()) {
			Notifications.GenerateErrorNotification(Notifications.ENTER_PASSWORD_MESSAGE);
		} else if (!password1.equals(password2)) {
			Notifications.GenerateErrorNotification(Notifications.MISMATCHING_PASSWORDS_MESSAGE);
		} else {
			try {
				authService.register(username, password1);
				UI.getCurrent().navigate("login");

				Notifications.GenerateSuccessNotification(Notifications.SUCCESSFUL_REGISTER_MESSAGE);
			} catch (Exception e) {
				Notifications.GenerateErrorNotification(Notifications.SOMETHING_WRONG_MESSAGE);
			}
		}
	}
}