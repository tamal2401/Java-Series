package com.okta.openid.OktaConnect.controller;

import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.User;
import com.okta.sdk.resource.user.UserBuilder;
import com.okta.sdk.resource.user.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    public Client client;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal OidcUser user) {
        return "Welcome, " + user.getFullName() + "!";
    }

    @GetMapping("/users")
    public UserList getUsers() {
        return client.listUsers();
    }

    @GetMapping("/user")
    public UserList searchUserByEmail(@RequestParam String query) {
        return client.listUsers(query, null, null, null, null);
    }

    @GetMapping("/createUser")
    public User createUser() {
        User user = UserBuilder.instance()
                .setEmail("normal.lewis@email.com")
                .setFirstName("Norman")
                .setLastName("Lewis")
                .setPassword("tempPassword".toCharArray())
                .setActive(true)
                .buildAndCreate(client);
        return user;
    }
}
