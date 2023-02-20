package KameleoonTrialTask.logic.controller;

import KameleoonTrialTask.logic.entity.Vote;
import KameleoonTrialTask.logic.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PutMapping("vote-plus/{id}")
    public ResponseEntity<String> plusVote(@PathVariable("id") Long quoteId) {
        return voteService.setVote(quoteId, 1);
    }

    @PutMapping("vote-minus/{id}")
    public ResponseEntity<String> minusVote(@PathVariable("id") Long quoteId) {
        return voteService.setVote(quoteId, -1);
    }

    @GetMapping("vote/{id}")
    public ResponseEntity<List<Map<String, Object>>> getStatistics(@PathVariable("id") Long quoteId) {
        List<Map<String, Object>> voteStatisticsList = voteService.getStatistics(quoteId);
        if (voteStatisticsList.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(voteStatisticsList);
    }

}
