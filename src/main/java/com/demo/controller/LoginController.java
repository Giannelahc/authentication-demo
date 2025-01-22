package com.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public LoginController(OAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }

    @GetMapping("/token")
    public String getToken(OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId(authentication.getAuthorizedClientRegistrationId())
                .principal(authentication)
                .build();

        // Autoriza el cliente y obtiene el token
        OAuth2AuthorizedClient authorizedClient = authorizedClientManager.authorize(authorizeRequest);


        if (authorizedClient == null) {
            throw new IllegalStateException("No se pudo autorizar el cliente.");
        }

        // Retorna el Access Token
        return "Access Token: " + authorizedClient.getAccessToken().getTokenValue();

    }
}
