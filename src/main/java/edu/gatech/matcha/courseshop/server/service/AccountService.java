package edu.gatech.matcha.courseshop.server.service;

import edu.gatech.matcha.courseshop.server.dto.AccountDto;

public interface AccountService {

    /**
     * Perform registration with given sign up information
     *
     * @param accountDto AccountDto containing new user credentials
     * @return true if registration successful, false if failed (e.g. duplicate accounts)
     */
    boolean signup(AccountDto accountDto);

    /**
     * Perform login credential check.
     *
     * @param accountDto AccountDto containing login credentials
     * @return true if valid credential, false if otherwise.
     */
    boolean login(AccountDto accountDto);
}
