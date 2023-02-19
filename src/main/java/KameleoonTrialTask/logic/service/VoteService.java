package KameleoonTrialTask.logic.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;

public interface VoteService {
    ResponseEntity<String> setVote(Long quoteId, User user, boolean isPositiveVote);
}
