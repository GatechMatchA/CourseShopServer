package edu.gatech.matcha.courseshop.server.controller;

import edu.gatech.matcha.courseshop.server.request.AccountRequest;
import edu.gatech.matcha.courseshop.server.response.Response;
import edu.gatech.matcha.courseshop.server.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity signup(@RequestBody @Valid AccountRequest request) {
        if (accountService.signup(request)) {
            return Response.create(HttpStatus.OK);
        } else {
            return Response.create(HttpStatus.BAD_REQUEST, "Unable to register.");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody @Valid AccountRequest request) {
        if (accountService.login(request)) {
            return Response.create(HttpStatus.OK);
        } else {
            return Response.create(HttpStatus.UNAUTHORIZED, "Invalid credentials.");
        }
    }
}
