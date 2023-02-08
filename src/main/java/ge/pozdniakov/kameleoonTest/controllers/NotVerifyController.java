package ge.pozdniakov.kameleoonTest.controllers;

import ge.pozdniakov.kameleoonTest.models.Quote;
import ge.pozdniakov.kameleoonTest.services.QuoteService;
import ge.pozdniakov.kameleoonTest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first")
public class NotVerifyController {

    private final QuoteService quoteService;
    private final UserService userService;

    @Autowired
    public NotVerifyController(QuoteService quoteService, UserService userService) {
        this.quoteService = quoteService;
        this.userService = userService;
    }

    @GetMapping
    public Quote showRandomQuote(){
        return quoteService.showRandom();
    }

}
