package edu.gatech.matcha.courseshop.server.service;

import edu.gatech.matcha.courseshop.server.model.Account;
import edu.gatech.matcha.courseshop.server.repository.AccountRepository;
import edu.gatech.matcha.courseshop.server.request.AccountRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean signup(AccountRequest accountRequest) {

        if (accountRepository.existsByUsername(accountRequest.getUsername())) {
            return false;
        }

        accountRepository.saveAndFlush(new Account().setUsername(accountRequest.getUsername())
                                                    .setPassword(passwordEncoder.encode(accountRequest.getPassword())));

        return true;
    }

    @Override
    public boolean login(AccountRequest accountRequest) {
        Account account = accountRepository.findByUsername(accountRequest.getUsername())
                                           .orElse(null);
        if (account == null) {
            return false;
        }
        return passwordEncoder.matches(accountRequest.getPassword(), account.getPassword());
    }
}
