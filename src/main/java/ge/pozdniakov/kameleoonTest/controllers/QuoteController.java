package ge.pozdniakov.kameleoonTest.controllers;

import ge.pozdniakov.kameleoonTest.dto.QuoteDTO;
import ge.pozdniakov.kameleoonTest.services.QuoteService;
import ge.pozdniakov.kameleoonTest.util.KameleoonException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addNewQuote(@RequestBody @Valid QuoteDTO quoteDTO,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error: errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new KameleoonException(errorMsg.toString());
        }
        quoteService.createNewQuote(quoteDTO);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateQuote(@RequestBody @Valid QuoteDTO quoteDTO,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error: errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new KameleoonException(errorMsg.toString());
        }
        quoteService.updateQuote(quoteDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuoteDTO> showQuoteOnId(@PathVariable Long id) {
        return ResponseEntity.ok(quoteService.getQuoteById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteQuote(@PathVariable Long id){
        quoteService.deleteQuote(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/random")
    public ResponseEntity<QuoteDTO> showRandomQuote(){
        return ResponseEntity.ok(quoteService.showRandom());
    }

    @GetMapping("/top")
    public ResponseEntity<List<QuoteDTO>> getTopQuotes(@RequestParam("count") int count){
        return ResponseEntity.ok(quoteService.qetTopQuotes(count));
    }

    @GetMapping("/flop")
    public ResponseEntity<List<QuoteDTO>> getFlopQuotes(@RequestParam("count") int count){
        return new ResponseEntity<>(quoteService.qetFlopQuotes(count), HttpStatus.OK);
    }

    @PostMapping("/{id}/increment")
    public ResponseEntity<HttpStatus> increaseVotes(@PathVariable Long id){
        quoteService.increaseVote(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/decrement")
    public ResponseEntity<HttpStatus> decreaseVotes(@PathVariable Long id){
        quoteService.decreaseVote(id);
        return ResponseEntity.ok().build();
    }
}
