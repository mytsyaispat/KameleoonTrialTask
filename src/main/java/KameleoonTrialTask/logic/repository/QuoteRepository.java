package KameleoonTrialTask.logic.repository;

import KameleoonTrialTask.logic.entity.Quote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long> {

    List<Quote> findAll();

    @Query(nativeQuery = true,
            value = "SELECT q.id as quote_id, q.content, u.username, vs.score " +
                    "FROM quote q " +
                    "JOIN vote_sum vs ON q.vote_sum_id = vs.id " +
                    "JOIN users u ON q.user_id = u.id " +
                    "ORDER BY vs.score DESC " +
                    "LIMIT 10")
    List<Map<String, Object>> getTopTenQuotes();


    @Query(nativeQuery = true,
            value = "SELECT q.id as quote_id, q.content, u.username, vs.score " +
                    "FROM quote q " +
                    "JOIN vote_sum vs ON q.vote_sum_id = vs.id " +
                    "JOIN users u ON q.user_id = u.id " +
                    "ORDER BY vs.score " +
                    "LIMIT 10")
    List<Map<String, Object>> getTenWorstQuotes();
}
