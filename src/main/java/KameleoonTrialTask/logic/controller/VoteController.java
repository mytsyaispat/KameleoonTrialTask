package KameleoonTrialTask.logic.controller;

import KameleoonTrialTask.logic.service.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
