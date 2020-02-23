package edu.gatech.matcha.courseshop.server.service;

import edu.gatech.matcha.courseshop.server.dto.AccountDto;
import edu.gatech.matcha.courseshop.server.model.Account;
import edu.gatech.matcha.courseshop.server.repository.AccountRepository;
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
    public boolean signup(AccountDto accountDto) {

        if (accountRepository.existsById(accountDto.getUsername())) {
            return false;
        }

        accountRepository.saveAndFlush(new Account().setUsername(accountDto.getUsername())
                                                    .setPassword(passwordEncoder.encode(accountDto.getPassword())));

        return true;
    }

    @Override
    public boolean login(AccountDto accountDto) {
        Account account = accountRepository.findById(accountDto.getUsername()).orElse(null);
        if (account == null) {
            return false;
        }
        return passwordEncoder.matches(accountDto.getPassword(), account.getPassword());
    }
}
