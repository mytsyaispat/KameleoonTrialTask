package KameleoonTrialTask.logic.repository;

import KameleoonTrialTask.logic.entity.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {
    Optional<Vote> findByQuoteIdAndUserId(Long quoteId, Long id);
}
