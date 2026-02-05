package dev.ailuruslabs.ailurusbank.domain.ports.output;

import dev.ailuruslabs.ailurusbank.domain.common.countries.Currency;

import java.util.Optional;

public interface CurrencyRepository {
    Currency saveCurrency(Currency currency);

    Optional<Currency> findByCountryCode(String countryCode);
    Optional<Currency> findBy
}
