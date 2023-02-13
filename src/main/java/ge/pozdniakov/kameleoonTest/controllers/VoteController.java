package ge.pozdniakov.kameleoonTest.controllers;

import ge.pozdniakov.kameleoonTest.dto.VoteDTO;
import ge.pozdniakov.kameleoonTest.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteController {

    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<VoteDTO>> getDataForGraph(@PathVariable Long id){
        return new ResponseEntity<>(voteService.getAllByQuote(id),HttpStatus.OK);
    }
}
