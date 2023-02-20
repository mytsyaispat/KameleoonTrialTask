package KameleoonTrialTask.logic.service.impl;

import KameleoonTrialTask.auth.entity.User;
import KameleoonTrialTask.auth.service.UserService;
import KameleoonTrialTask.config.ParamConfig;
import KameleoonTrialTask.logic.entity.Quote;
import KameleoonTrialTask.logic.entity.VoteSum;
import KameleoonTrialTask.logic.repository.QuoteRepository;
import KameleoonTrialTask.logic.service.QuoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final ParamConfig paramConfig;
    private final UserService userService;

    public QuoteServiceImpl(QuoteRepository quoteRepository, ParamConfig paramConfig, UserService userService) {
        this.quoteRepository = quoteRepository;
        this.paramConfig = paramConfig;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<String> createQuote(Quote quoteData) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.getUserByUsername(authentication.getName());
        Quote quote = new Quote(quoteData.getContent(), LocalDateTime.now(), user.orElse(null), new VoteSum());
        quoteRepository.save(quote);
        return ResponseEntity.ok("Quote successfully created!");
    }

    @Override
    public Optional<Quote> getQuote(Long id) {
        return quoteRepository.findById(id);
    }

    @Override
    public Optional<Quote> getRandomQuote() {
        List<Quote> quoteList = quoteRepository.findAll();
        if (quoteList.isEmpty()) return Optional.empty();
        return Optional.of(quoteList.get(paramConfig.getRandom().nextInt(quoteList.size())));
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteQuoteById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Quote> optionalQuote = getQuote(id);
        if (optionalQuote.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quote not found!");
        if (!optionalQuote.get().getUser().getUsername().equals(authentication.getName()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This quote does not belong to you!");
        quoteRepository.deleteById(id);
        return ResponseEntity.ok("Quote successfully removed!");
    }

    @Override
    public List<Map<String, Object>> getTopTenQuotes() {
        return quoteRepository.getTopTenQuotes();
    }

    @Override
    public List<Map<String, Object>> getTenWorstQuotes() {
        return quoteRepository.getTenWorstQuotes();
    }

    @Override
    public void saveQuote(Quote quote) {
        quoteRepository.save(quote);
    }
}
