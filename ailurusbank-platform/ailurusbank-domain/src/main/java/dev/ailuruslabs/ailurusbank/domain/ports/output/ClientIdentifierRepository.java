package dev.ailuruslabs.ailurusbank.domain.ports.output;

import dev.ailuruslabs.ailurusbank.domain.clients.identifiers.ClientIdentifier;
import dev.ailuruslabs.ailurusbank.domain.clients.identifiers.IdentifierDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientIdentifierRepository {
    ClientIdentifier createClientIdentifier(IdentifierDetails details);

    Optional<ClientIdentifier> findByDetails(IdentifierDetails details);
    List<ClientIdentifier> findAllByClientUUID(UUID clientUUID);
}
