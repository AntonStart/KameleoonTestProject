package ge.pozdniakov.kameleoonTest.services;

import ge.pozdniakov.kameleoonTest.dto.QuoteDTO;
import ge.pozdniakov.kameleoonTest.models.Quote;
import ge.pozdniakov.kameleoonTest.models.User;
import ge.pozdniakov.kameleoonTest.models.Vote;
import ge.pozdniakov.kameleoonTest.repositories.QuoteRepository;
import ge.pozdniakov.kameleoonTest.repositories.UserRepository;
import ge.pozdniakov.kameleoonTest.repositories.VoteRepository;
import ge.pozdniakov.kameleoonTest.util.Converter;
import jakarta.persistence.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final VoteRepository voteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository, VoteRepository voteRepository) {
        this.quoteRepository = quoteRepository;
        this.voteRepository = voteRepository;
    }

    public List<Quote> findAllByUser(User user){
        return quoteRepository.findAllByUser(user);
    }

    public List<Quote> findAll(){
        return quoteRepository.findAll();
    }

    public void addNewQuote(QuoteDTO quoteDTO){
        Quote quote = Converter.convertToQuote(quoteDTO);

        quote.setCurrentVote(0L);
        quote.setCreatedAt(LocalDateTime.now());
        quote.setUpdatedAt(LocalDateTime.now());

        quoteRepository.save(quote);
    }

    public void updateQuote(QuoteDTO quoteDTO){
        Quote quote = Converter.convertToQuote(quoteDTO);

        quote.setUpdatedAt(LocalDateTime.now());

        quoteRepository.save(quote);
    }

    public void deleteQuote(Long id){
        quoteRepository.deleteById(id);
    }

    //todo refactoring this method
    public Quote showRandom(){
        Long randomId = ThreadLocalRandom.current().nextLong(quoteRepository.count());
        return quoteRepository
                .findAll()
                .get(randomId.intValue());
    }



    public void decreaseVote(QuoteDTO quoteDTO) {
        Vote vote = new Vote();
        vote.setCurrentRate(quoteDTO.getCurrentVotes() - 1);
        addNewQuoteAndVoteInDB(quoteDTO, vote);
    }


    public void increaseVote(QuoteDTO quoteDTO) {
        Vote vote = new Vote();
        vote.setCurrentRate(quoteDTO.getCurrentVotes() + 1);
        addNewQuoteAndVoteInDB(quoteDTO, vote);
    }

    @Transactional
    void addNewQuoteAndVoteInDB(QuoteDTO quoteDTO, Vote vote) {
        vote.setDateOfVoting(LocalDateTime.now());
        vote.setQuote(getQuoteById(quoteDTO.getId()));

        voteRepository.save(vote);

        Quote quote = getQuoteById(quoteDTO.getId());

        quote.setCurrentVote(vote.getCurrentRate());

        quoteRepository.save(quote);
    }

    public Quote getQuoteById(Long id){
        return quoteRepository.findById(id).orElse(null);
    }

    public List<Quote> qetTopQuotes(int count){
        return quoteRepository
                .findAll(PageRequest.of(0, count, Sort.Direction.DESC, "current_vote"))
                .toList();
    }

    public List<Quote> qetFlopQuotes(int count){
        return quoteRepository
                .findAll(PageRequest.of(0, count, Sort.Direction.ASC, "current_vote"))
                .toList();
    }
}
