package ge.pozdniakov.kameleoonTest.services;

import ge.pozdniakov.kameleoonTest.dto.QuoteDTO;
import ge.pozdniakov.kameleoonTest.models.Quote;
import ge.pozdniakov.kameleoonTest.models.Vote;
import ge.pozdniakov.kameleoonTest.repositories.QuoteRepository;
import ge.pozdniakov.kameleoonTest.repositories.VoteRepository;
import ge.pozdniakov.kameleoonTest.util.Converter;
import ge.pozdniakov.kameleoonTest.util.QuoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final VoteRepository voteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository, VoteRepository voteRepository) {
        this.quoteRepository = quoteRepository;
        this.voteRepository = voteRepository;
    }

    //CREATE
    public void createNewQuote(QuoteDTO quoteDTO){
        Quote quote = Converter.convertToQuote(quoteDTO);

        quote.setCurrentVote(0L);
        quote.setCreatedAt(LocalDateTime.now());
        quote.setUpdatedAt(LocalDateTime.now());

        quoteRepository.save(quote);
    }

    //READ
    public QuoteDTO getQuoteById(Long id){
        return Converter
                .convertToQuoteDTO(quoteRepository.findById(id).orElseThrow(QuoteNotFoundException::new));
    }

    //UPDATE
    public void updateQuote(QuoteDTO quoteDTO){
        Quote quote = Converter.convertToQuote(quoteDTO);

        quote.setUpdatedAt(LocalDateTime.now());

        quoteRepository.save(quote);
    }

    //DELETE
    public void deleteQuote(Long id){
        quoteRepository.deleteById(id);
    }

    //READ RANDOM
    public QuoteDTO showRandom(){
        Long randomId = ThreadLocalRandom.current().nextLong(quoteRepository.count());
        return Converter.convertToQuoteDTO(
                quoteRepository
                .findAll()
                .get(randomId.intValue()));
    }

    //INCREASE VOTE
    public void increaseVote(Long id) {
        Vote vote = new Vote();
        Quote quote = quoteRepository.findById(id).orElseThrow(QuoteNotFoundException::new);
        vote.setCurrentRate(quote.getCurrentVote() + 1);
        addNewQuoteAndVoteInDB(quote, vote);
    }

    //DECREASE VOTE
    public void decreaseVote(Long id) {
        Vote vote = new Vote();
        Quote quote = quoteRepository.findById(id).orElseThrow(QuoteNotFoundException::new);
        vote.setCurrentRate(quote.getCurrentVote() - 1);
        addNewQuoteAndVoteInDB(quote, vote);
    }

    //auxiliary method for voting
    @Transactional
    void addNewQuoteAndVoteInDB(Quote quote, Vote vote) {
        quote.setCurrentVote(vote.getCurrentRate());
        vote.setDateOfVoting(LocalDateTime.now());
        vote.setQuote(quote);
        voteRepository.save(vote);
        quoteRepository.save(quote);
    }

    //READ TOP QUOTES
    public List<QuoteDTO> qetTopQuotes(int count){
        return quoteRepository
                .findAll(PageRequest.of(0, count, Sort.Direction.DESC, "currentVote"))
                .toList()
                .stream()
                .map(quote -> Converter.convertToQuoteDTO(quote))
                .collect(Collectors.toList());
    }

    //READ FLOP QUOTES
    public List<QuoteDTO> qetFlopQuotes(int count){
        return quoteRepository
                .findAll(PageRequest.of(0, count, Sort.Direction.ASC, "currentVote"))
                .toList()
                .stream()
                .map(quote -> Converter.convertToQuoteDTO(quote))
                .collect(Collectors.toList());
    }
}
