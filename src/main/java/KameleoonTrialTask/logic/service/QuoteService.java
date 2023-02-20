package KameleoonTrialTask.logic.service;

import KameleoonTrialTask.logic.entity.Quote;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface QuoteService {


    ResponseEntity<String> createQuote(Quote quote);

    void saveQuote(Quote quote);

    Optional<Quote> getQuote(Long id);

    Optional<Quote> getRandomQuote();

    ResponseEntity<String> deleteQuoteById(Long id);

    List<Map<String, Object>> getTopTenQuotes();

    List<Map<String, Object>> getTenWorstQuotes();

}
