package ge.pozdniakov.kameleoonTest.util;

import ge.pozdniakov.kameleoonTest.dto.QuoteDTO;
import ge.pozdniakov.kameleoonTest.dto.UserDTO;
import ge.pozdniakov.kameleoonTest.models.Quote;
import ge.pozdniakov.kameleoonTest.models.User;
import ge.pozdniakov.kameleoonTest.repositories.QuoteRepository;
import ge.pozdniakov.kameleoonTest.repositories.UserRepository;
import ge.pozdniakov.kameleoonTest.repositories.VoteRepository;

import java.util.stream.Collectors;

public class Converter {


    public static User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public static UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setQuotes(user.getQuotes()
                .stream()
                .map(quote -> convertToQuoteDTO(quote))
                .collect(Collectors.toList()));
        return userDTO;
    }

    public static Quote convertToQuote(QuoteDTO quoteDTO){
        Quote quote = new Quote();

        quote.setId(quoteDTO.getId());
        quote.setContent(quoteDTO.getContent());
        quote.setCurrentVote(quoteDTO.getCurrentVotes());
        User user = new User();
        user.setUsername(quoteDTO.getUsername());
        quote.setUser(user);
        quote.setCreatedAt(quoteDTO.getUpdatedAt());
        quote.setUpdatedAt(quoteDTO.getUpdatedAt());

        return quote;
    }

    public static QuoteDTO convertToQuoteDTO(Quote quote){
        QuoteDTO quoteDTO = new QuoteDTO();

        quoteDTO.setId(quote.getId());
        quoteDTO.setContent(quote.getContent());
        quoteDTO.setCurrentVotes(quote.getCurrentVote());
        quoteDTO.setUsername(quote.getUser().getUsername());
        quoteDTO.setCreatedAt(quote.getCreatedAt());
        quoteDTO.setUpdatedAt(quote.getUpdatedAt());

        return quoteDTO;
    }
}
