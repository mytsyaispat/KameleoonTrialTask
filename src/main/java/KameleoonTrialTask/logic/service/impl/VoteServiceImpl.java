package KameleoonTrialTask.logic.service.impl;

import KameleoonTrialTask.auth.entity.User;
import KameleoonTrialTask.auth.service.UserService;
import KameleoonTrialTask.logic.entity.Quote;
import KameleoonTrialTask.logic.entity.Vote;
import KameleoonTrialTask.logic.repository.VoteRepository;
import KameleoonTrialTask.logic.service.QuoteService;
import KameleoonTrialTask.logic.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
    public ResponseEntity<String> setVote(Long quoteId, org.springframework.security.core.userdetails.User userData, boolean isPositiveVote) {
        Optional<User> optionalUser = userService.getUserByUsername(userData.getUsername());
        if (optionalUser.isEmpty()) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You must be registered!");
        User user = optionalUser.get();
        Optional<Vote> optionalVote = voteRepository.findByQuoteIdAndUserId(quoteId, user.getId());
        if (optionalVote.isPresent()) {
            Vote vote = optionalVote.get();
            if (isPositiveVote) {
                if (vote.isPositiveVote()) return ResponseEntity.ok("The vote has already been added!");
                vote.setPositiveVote(true);
                voteRepository.save(vote);
                return ResponseEntity.ok("The vote has been successfully added!");
            }
            if (!vote.isPositiveVote()) return ResponseEntity.ok("The vote has already been added!");
            vote.setPositiveVote(false);
            voteRepository.save(vote);
            return ResponseEntity.ok("The vote has been successfully added!");
        }
        Optional<Quote> optionalQuote = quoteService.getQuote(quoteId);
        if (optionalQuote.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quote not found!");
        Vote vote = new Vote(isPositiveVote, user, optionalQuote.get());
        voteRepository.save(vote);
        return ResponseEntity.ok("The vote has been successfully added!");
    }
}
