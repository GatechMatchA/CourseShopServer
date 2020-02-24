package edu.gatech.matcha.courseshop.server.service;

import edu.gatech.matcha.courseshop.server.request.AccountRequest;

public interface AccountService {

    /**
     * Perform registration with given sign up information
     *
     * @param accountRequest AccountDto containing new user credentials
     * @return true if registration successful, false if failed (e.g. duplicate accounts)
     */
    boolean signup(AccountRequest accountRequest);

    /**
     * Perform login credential check.
     *
     * @param accountRequest AccountDto containing login credentials
     * @return true if valid credential, false if otherwise.
     */
    boolean login(AccountRequest accountRequest);
}
