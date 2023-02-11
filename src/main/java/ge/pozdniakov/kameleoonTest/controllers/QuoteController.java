package ge.pozdniakov.kameleoonTest.controllers;

import ge.pozdniakov.kameleoonTest.dto.QuoteDTO;
import ge.pozdniakov.kameleoonTest.models.Quote;
import ge.pozdniakov.kameleoonTest.services.QuoteService;
import ge.pozdniakov.kameleoonTest.services.UserService;
import ge.pozdniakov.kameleoonTest.services.VoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
    public void addNewQuote(@RequestBody QuoteDTO quoteDTO) {
        quoteService.addNewQuote(quoteDTO);
    }

    @GetMapping("/random")
    public QuoteDTO getRandomQuote(){
        return convertToQuoteDTO(quoteService.showRandom());
    }

    //todo adding this method
    @GetMapping("/top")
    public List<QuoteDTO> getTopQuotes(@RequestParam("count") int count){
        return null;
    }

    //todo adding this method
    @GetMapping("/flop")
    public List<QuoteDTO> getFlopQuotes(@RequestParam("count") int count){
        return null;
    }

    @PostMapping("/increment")
    public void increaseVotes(@RequestBody QuoteDTO quoteDTO){
        quoteService.increaseVote(quoteDTO);
    }

    @PostMapping("/decrement")
    public void decreaseVotes(@RequestBody QuoteDTO quoteDTO){
        quoteService.decreaseVote(quoteDTO);
    }

    private QuoteDTO convertToQuoteDTO(Quote quote){
        QuoteDTO quoteDTO = new QuoteDTO();
        quoteDTO.setContent(quote.getContent());
        quoteDTO.setCurrentVotes(quote.getCurrentVote());
        quoteDTO.setUsername(quote.getUser().getUsername());

        return quoteDTO;
    }

}
