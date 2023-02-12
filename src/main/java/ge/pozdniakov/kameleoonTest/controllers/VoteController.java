package ge.pozdniakov.kameleoonTest.controllers;

import ge.pozdniakov.kameleoonTest.dto.VoteDTO;
import ge.pozdniakov.kameleoonTest.services.VoteService;
import ge.pozdniakov.kameleoonTest.util.KameleoonTestErrorResponse;
import ge.pozdniakov.kameleoonTest.util.QuoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ExceptionHandler
    private ResponseEntity<KameleoonTestErrorResponse> handleException(QuoteNotFoundException quoteNotFoundException){
        KameleoonTestErrorResponse personErrorResponse = new KameleoonTestErrorResponse(
                "Quote with this id was not found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(personErrorResponse, HttpStatus.NOT_FOUND);
    }
}
