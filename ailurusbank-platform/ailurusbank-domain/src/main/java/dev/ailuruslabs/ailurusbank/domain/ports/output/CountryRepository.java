package dev.ailuruslabs.ailurusbank.domain.ports.output;

import dev.ailuruslabs.ailurusbank.domain.common.countries.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    Country saveCountry(Country country);

    Optional<Country> findByAlpha2(String alpha2);
    Optional<Country> findByAlpha3(String alpha3);
    List<Country> findAllByName(String name);
    List<Country> findAllByCurrencyCode(String currencyCode);
}
