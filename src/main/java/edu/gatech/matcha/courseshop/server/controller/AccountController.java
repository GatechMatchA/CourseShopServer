package edu.gatech.matcha.courseshop.server.controller;

import edu.gatech.matcha.courseshop.server.dto.AccountDto;
import edu.gatech.matcha.courseshop.server.request.AccountRequest;
import edu.gatech.matcha.courseshop.server.response.Response;
import edu.gatech.matcha.courseshop.server.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Response signup(@RequestBody @Valid AccountRequest request) {
        if (accountService.signup(new AccountDto()
                                  .setUsername(request.getUsername())
                                  .setPassword(request.getPassword()))) {
            return Response.withStatus(HttpStatus.OK);
        } else {
            return Response.withStatus(HttpStatus.BAD_REQUEST).addError("Unable to register");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody @Valid AccountRequest request) {
        if (accountService.login(new AccountDto()
                                 .setUsername(request.getUsername())
                                 .setPassword(request.getPassword()))) {
            return Response.withStatus(HttpStatus.OK);
        } else {
            return Response.withStatus(HttpStatus.UNAUTHORIZED).addError("Invalid credentials.");
        }
    }
}
