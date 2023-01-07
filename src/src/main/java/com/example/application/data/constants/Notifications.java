package com.example.application.data.constants;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

public class Notifications {
    public static final String ENTER_USERNAME_MESSAGE = "Enter a username";
    public static final String ENTER_PASSWORD_MESSAGE = "Enter a password";
    public static final String MISMATCHING_PASSWORDS_MESSAGE = "Passwords don't match";
    public static final String SOMETHING_WRONG_MESSAGE = "Something went wrong!";
    public static final String USERNAME_PASSWORD_WRONG_MESSAGE = "Username or Password are not correct!";

    public static final String SUCCESSFUL_LOGIN_MESSAGE = "Successfully logged in";
    public static final String SUCCESSFUL_REGISTER_MESSAGE = "Successfully registered. Log in to continue";
    public static final String SUCCESSFUL_LOGOUT_MESSAGE = "Successfully logged out.";
    public static final String SUCCESSFUL_CREATE_CONTACT_MESSAGE = "Successfully updated contact";
    public static final String SUCCESSFUL_DELETED_CONTACT_MESSAGE = "Successfully deleted contact";

    public static void GenerateSuccessNotification(String message) {
        Notification notification = Notification.show(message);
        notification.setPosition(Notification.Position.TOP_END);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }

    public static void GenerateErrorNotification(String message) {
        Notification notification = Notification.show(message);
        notification.setPosition(Notification.Position.TOP_END);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
    }
}
