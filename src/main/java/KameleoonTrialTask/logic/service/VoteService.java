package KameleoonTrialTask.logic.service;

import org.springframework.http.ResponseEntity;

public interface VoteService {
    ResponseEntity<String> setVote(Long quoteId, int voteNumber);
}
