package KameleoonTrialTask.logic.service.impl;

import KameleoonTrialTask.auth.entity.User;
import KameleoonTrialTask.auth.service.UserService;
import KameleoonTrialTask.logic.entity.Quote;
import KameleoonTrialTask.logic.entity.Vote;
import KameleoonTrialTask.logic.entity.VoteSum;
import KameleoonTrialTask.logic.repository.VoteRepository;
import KameleoonTrialTask.logic.service.QuoteService;
import KameleoonTrialTask.logic.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;
    private final QuoteService quoteService;
    private final UserService userService;

    public VoteServiceImpl(VoteRepository voteRepository, QuoteService quoteService, UserService userService) {
        this.voteRepository = voteRepository;
        this.quoteService = quoteService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public ResponseEntity<String> setVote(Long quoteId, int voteNumber) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> optionalUser = userService.getUserByUsername(authentication.getName());
        if (optionalUser.isEmpty()) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You must be registered!");
        User user = optionalUser.get();
        Optional<Vote> optionalVote = voteRepository.findByQuoteIdAndUserId(quoteId, user.getId());
        Optional<Quote> optionalQuote = quoteService.getQuote(quoteId);
        if (optionalQuote.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quote not found!");
        Quote quote = optionalQuote.get();
        VoteSum voteSum = quote.getVoteSum();
        if (optionalVote.isPresent()) {
            Vote vote = optionalVote.get();
            if (voteNumber == 1) {
                if (vote.getVoteNumber() == voteNumber) return ResponseEntity.ok("The vote has already been added!");
                vote.setVoteNumber(voteNumber);
                voteRepository.save(vote);
                voteSum.fromMinusToPlus();
                quote.setVoteSum(voteSum);
                quoteService.saveQuote(quote);
                return ResponseEntity.ok("The vote has been successfully added!");
            }
            if (vote.getVoteNumber() == voteNumber) return ResponseEntity.ok("The vote has already been added!");
            vote.setVoteNumber(voteNumber);
            voteRepository.save(vote);
            voteSum.fromPlusToMinus();
            quote.setVoteSum(voteSum);
            quoteService.saveQuote(quote);
            return ResponseEntity.ok("The vote has been successfully added!");
        }
        if (voteNumber == 1) voteSum.incrementScore();
        else voteSum.decrementScore();
        quote.setVoteSum(voteSum);
        quoteService.saveQuote(quote);
        Vote vote = new Vote(voteNumber, user, quote);
        voteRepository.save(vote);
        return ResponseEntity.ok("The vote has been successfully added!");
    }
}
