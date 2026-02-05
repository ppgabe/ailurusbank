package dev.ailuruslabs.ailurusbank.domain.ports.output;

import dev.ailuruslabs.ailurusbank.domain.accounts.Account;
import dev.ailuruslabs.ailurusbank.domain.accounts.AccountNumber;
import dev.ailuruslabs.ailurusbank.domain.accounts.AccountDetails;
import dev.ailuruslabs.ailurusbank.domain.clients.identifiers.ClientIdentifier;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account createAccount(AccountDetails details);
    Optional<Account> findByAccountNumber(AccountNumber number);
    boolean existsByAccountNumber(AccountNumber number);
    List<Account> findAllByBankInstitutionCode(String institutionCode);
    List<Account> findAllByClientIdentifier(ClientIdentifier identifier);
}