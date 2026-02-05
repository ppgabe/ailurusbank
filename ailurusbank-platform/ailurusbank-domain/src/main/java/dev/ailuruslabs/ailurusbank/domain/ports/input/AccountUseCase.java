package dev.ailuruslabs.ailurusbank.domain.ports.input;

import dev.ailuruslabs.ailurusbank.domain.accounts.Account;
import dev.ailuruslabs.ailurusbank.domain.accounts.AccountDetails;
import dev.ailuruslabs.ailurusbank.domain.clients.Client;

public interface AccountUseCase {
    Account openAccount(AccountDetails details);
    Account openAccount(AccountDetails details, Client owner);

    Account activateAccount(Account account);

    Account closeAccount(Account account);

    Account freezeAccount(Account account);
}
