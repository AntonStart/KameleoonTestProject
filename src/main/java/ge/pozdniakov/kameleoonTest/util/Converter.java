package ge.pozdniakov.kameleoonTest.util;

import ge.pozdniakov.kameleoonTest.dto.QuoteDTO;
import ge.pozdniakov.kameleoonTest.dto.UserDTO;
import ge.pozdniakov.kameleoonTest.dto.VoteDTO;
import ge.pozdniakov.kameleoonTest.models.Quote;
import ge.pozdniakov.kameleoonTest.models.User;
import ge.pozdniakov.kameleoonTest.models.Vote;

import java.util.stream.Collectors;

public class Converter {


    public static User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public static UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
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

    public static VoteDTO convertToVoteDTO(Vote vote){
        VoteDTO voteDTO = new VoteDTO();
        
        voteDTO.setId(vote.getId());
        voteDTO.setCurrentVotes(vote.getCurrentRate());
        voteDTO.setDateOfVoting(vote.getDateOfVoting());
        voteDTO.setQuoteId(vote.getQuote().getId());

        return voteDTO;
    }
}
