package dev.ailuruslabs.ailurusbank.domain.ports.output;

import dev.ailuruslabs.ailurusbank.domain.clients.identifiers.IssuingAuthority;

import java.util.List;
import java.util.Optional;

public interface IssuingAuthorityRepository {
    IssuingAuthority createIssuingAuthority(IssuingAuthority issuingAuthority);

    Optional<IssuingAuthority> findByCode(String code);
    List<IssuingAuthority> findAllByCountryCode(String countryCode);
    List<IssuingAuthority> findAllByName(String name);
}