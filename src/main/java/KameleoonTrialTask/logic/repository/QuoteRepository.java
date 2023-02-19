package KameleoonTrialTask.logic.repository;

import KameleoonTrialTask.logic.entity.Quote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long> {

    List<Quote> findAll();

//    @Query(nativeQuery = true,
//    value = "SELECT COUNT(v FROM quote q JOIN vote v ON q.id = v.quote_id  ")
//    List<Quote> getTopTenQuotes();
//
//    List<Quote> getTenWorstQuotes();
}
