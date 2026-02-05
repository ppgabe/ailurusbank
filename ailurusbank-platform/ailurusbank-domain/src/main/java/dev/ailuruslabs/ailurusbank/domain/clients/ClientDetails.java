package dev.ailuruslabs.ailurusbank.domain.clients;

public sealed interface ClientDetails permits OrganizationDetails, PersonalDetails {
}
