package edu.gatech.matcha.courseshop.server.util;

import edu.gatech.matcha.courseshop.server.request.AccountRequest;
import edu.gatech.matcha.courseshop.server.service.AccountService;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class BasicAuth {

    private static AccountService accountService;

    public BasicAuth(AccountService accountService) {
        BasicAuth.accountService = accountService;
    }

    public static AccountRequest decode(String authHeader) {
        if (authHeader == null || !authHeader.toLowerCase()
                                             .startsWith("basic")) {
            return null;
        }
        String base64Credentials = authHeader.substring("Basic".length())
                                             .trim();
        byte[] credDecoded = Base64.getDecoder()
                                   .decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        final String[] values = credentials.split(":", 2);
        return new AccountRequest().setUsername(values[0])
                                   .setPassword(values[1]);
    }

    public static boolean authenticated(AccountRequest accountRequest) {
        if (accountRequest == null) {
            return false;
        }
        return accountService.login(accountRequest);
    }

    public static boolean authenticated(String authHeader) {
        AccountRequest accountRequest = decode(authHeader);
        return authenticated(accountRequest);
    }
}
