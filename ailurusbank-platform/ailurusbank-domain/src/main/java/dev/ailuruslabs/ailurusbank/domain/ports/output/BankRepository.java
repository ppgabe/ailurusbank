package dev.ailuruslabs.ailurusbank.domain.ports.output;

import dev.ailuruslabs.ailurusbank.domain.banks.Bank;
import dev.ailuruslabs.ailurusbank.domain.banks.BankDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BankRepository {
    Bank createBank(BankDetails details);

    Optional<Bank> findByInstitutionCode(String institutionCode);
    Optional<Bank> findByUUID(UUID uuid);

    List<Bank> findAllByCountryCode(String countryCode);
    List<Bank> findAllByName(String name);

    List<Bank> findAllByCountryAndLocationCode(String countryCode, String locationCode);
}
