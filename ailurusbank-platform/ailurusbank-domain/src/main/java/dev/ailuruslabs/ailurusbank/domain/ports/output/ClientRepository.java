package dev.ailuruslabs.ailurusbank.domain.ports.output;

import dev.ailuruslabs.ailurusbank.domain.clients.*;
import dev.ailuruslabs.ailurusbank.domain.clients.identifiers.ClientIdentifier;
import dev.ailuruslabs.ailurusbank.domain.clients.identifiers.IdentifierDetails;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Individual createIndividual(PersonalDetails details);
    Organization createOrganizationDetails(OrganizationDetails details);

    Optional<Client> findByIdentifierDetails(IdentifierDetails details);
    Optional<Client> findByClientIdentifier(ClientIdentifier identifier);

    boolean existsByClientIdentifier(ClientIdentifier identifier);
    boolean existsByIdentifierDetails(IdentifierDetails details);
}
