package ge.pozdniakov.kameleoonTest.services;

import ge.pozdniakov.kameleoonTest.models.Quote;
import ge.pozdniakov.kameleoonTest.models.Vote;
import ge.pozdniakov.kameleoonTest.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public List<Vote> getAllByQuote(Quote quote){
        return voteRepository.findAllByQuoteOrderByDateOfVoting(quote);
    }
}
