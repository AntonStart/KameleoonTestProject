package ge.pozdniakov.kameleoonTest.controllers;

import ge.pozdniakov.kameleoonTest.models.Quote;
import ge.pozdniakov.kameleoonTest.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/random")
    public Quote getRandomQuote(){
        return quoteService.showRandom();
    }

    //todo adding this method
    @GetMapping("/top")
    public List<Quote> getTopQuotes(@RequestParam("count") int count){
        return null;
    }

    //todo adding this method
    @GetMapping("/flop")
    public List<Quote> getFlopQuotes(@RequestParam("count") int count){
        return null;
    }

}
