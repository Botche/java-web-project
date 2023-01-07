package com.example.application.data.service;

import com.example.application.data.entity.Roles;
import com.example.application.data.entity.Users;
import com.example.application.data.repository.UserRepository;
import com.example.application.views.*;
import com.example.application.views.list.ListView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouteData;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    public record AuthorizedRoute(String route, String name, Class<? extends Component> view) {

    }

    public static class AuthException extends Exception {

    }

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void authenticate(String username, String password) throws AuthException {
        Users user = userRepository.getByUsername(username);
        if (user != null && user.checkPassword(password) && user.isActive()) {
            VaadinSession.getCurrent().setAttribute(Users.class, user);

            configureRoutes(user.getRoleId());
        } else {
            throw new AuthException();
        }
    }

    public Users getAuthenticatedUser() {
        return VaadinSession.getCurrent().getAttribute(Users.class);
    }

    private void configureRoutes(Roles role) {
        removeAuthenticationRoutes();
        List<RouteData> routes = RouteConfiguration.forSessionScope().getAvailableRoutes();
        getAuthorizedRoutes(role)
                .forEach(route ->
                        RouteConfiguration.forSessionScope().setRoute(
                                route.route, route.view, MainLayout.class));
    }

    private void removeAuthenticationRoutes() {
        RouteConfiguration.forApplicationScope().removeRoute(LoginView.class);
        RouteConfiguration.forApplicationScope().removeRoute(RegisterView.class);
    }

    public List<AuthorizedRoute> getAuthorizedRoutes(Roles role) {
        var routes = new ArrayList<AuthorizedRoute>();

        routes.add(new AuthorizedRoute("", "Home", ListView.class));
        routes.add(new AuthorizedRoute("contacts", "Home", ListView.class));
        routes.add(new AuthorizedRoute("logout", "Logout", LogoutView.class));
        System.out.println(role);
        if (role.equals(Roles.ADMIN)) {
            routes.add(new AuthorizedRoute("dashboard", "Admin", DashboardView.class));
        }

        return routes;
    }

    public void register(String username, String password) {
        Users user = new Users(username, password, Roles.USER);
        user.setActive(true);
        userRepository.save(user);
    }

}