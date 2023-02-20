package KameleoonTrialTask.logic.repository;

import KameleoonTrialTask.logic.entity.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {
    Optional<Vote> findByQuoteIdAndUserId(Long quoteId, Long id);

    @Query(nativeQuery = true,
    value = "SELECT v.date, v.vote_number FROM vote v WHERE v.quote_id = :quote_id")
    List<Map<String, Object>> findAllByQuoteId(@Param("quote_id") Long quoteId);


}
