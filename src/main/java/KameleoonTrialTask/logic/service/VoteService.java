package KameleoonTrialTask.logic.service;

import KameleoonTrialTask.logic.entity.Vote;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface VoteService {
    ResponseEntity<String> setVote(Long quoteId, int voteNumber);

    List<Map<String, Object>> getStatistics(Long quoteId);
}
