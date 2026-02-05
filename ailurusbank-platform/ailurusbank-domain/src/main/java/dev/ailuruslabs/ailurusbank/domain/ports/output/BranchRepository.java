package dev.ailuruslabs.ailurusbank.domain.ports.output;

import dev.ailuruslabs.ailurusbank.domain.banks.Bank;
import dev.ailuruslabs.ailurusbank.domain.banks.Branch;
import dev.ailuruslabs.ailurusbank.domain.banks.BranchDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BranchRepository {
    Branch createBranch(String institutionCode, BranchDetails details);
    Branch createBranch(UUID bankId, BranchDetails details);

    Optional<Branch> findByBic(String bic);
    List<Branch> findAllByInstitutionCode(String institutionCode);
    List<Branch> findAllByDetails(BranchDetails details);
}
