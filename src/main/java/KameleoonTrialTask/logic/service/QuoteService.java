package KameleoonTrialTask.logic.service;

import KameleoonTrialTask.logic.entity.Quote;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface QuoteService {


    ResponseEntity<String> createQuote(Quote quote);

    Optional<Quote> getQuote(Long id);

    Optional<Quote> getRandomQuote();

    ResponseEntity<String> deleteQuoteById(Long id);

    List<Quote> getTopTenQuotes();

    List<Quote> getTenWorstQuotes();

}
