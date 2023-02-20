package KameleoonTrialTask.logic.controller;

import KameleoonTrialTask.logic.entity.Quote;
import KameleoonTrialTask.logic.service.QuoteService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class QuoteController {
    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping("quote")
    public ResponseEntity<String> createQuote(@RequestBody @Valid Quote quote) {
        return quoteService.createQuote(quote);
    }

    @GetMapping("quote/{id}")
    public ResponseEntity<Quote> getQuote(@PathVariable Long id) {
        Optional<Quote> optionalQuote = quoteService.getQuote(id);
        if (optionalQuote.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quote not found!");
        return ResponseEntity.ok(optionalQuote.get());
    }

    @GetMapping("quote")
    public ResponseEntity<Quote> getRandomQuote() {
        Optional<Quote> optionalQuote = quoteService.getRandomQuote();
        if (optionalQuote.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quote not found!");
        return ResponseEntity.ok(optionalQuote.get());
    }

    @DeleteMapping("quote/{id}")
    public ResponseEntity<String> deleteQuoteById(@PathVariable Long id) {
        return quoteService.deleteQuoteById(id);
    }

    @GetMapping("quotes-top")
    public ResponseEntity<List<Map<String, Object>>> getTopTenQuotes() {
        List<Map<String, Object>> quoteList = quoteService.getTopTenQuotes();
        if (quoteList.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(quoteList);
    }

    @GetMapping("quotes-worst")
    public ResponseEntity<List<Map<String, Object>>> getTenWorstQuotes() {
        List<Map<String, Object>> quoteList = quoteService.getTenWorstQuotes();
        if (quoteList.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(quoteList);
    }
}
