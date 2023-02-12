package ge.pozdniakov.kameleoonTest.services;

import ge.pozdniakov.kameleoonTest.dto.VoteDTO;
import ge.pozdniakov.kameleoonTest.models.Quote;
import ge.pozdniakov.kameleoonTest.repositories.QuoteRepository;
import ge.pozdniakov.kameleoonTest.repositories.VoteRepository;
import ge.pozdniakov.kameleoonTest.util.Converter;
import ge.pozdniakov.kameleoonTest.util.QuoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final QuoteRepository quoteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository, QuoteRepository quoteRepository) {
        this.voteRepository = voteRepository;
        this.quoteRepository = quoteRepository;
    }

    //READ DATA FROM GRAPH VOTE/TIME
    public List<VoteDTO> getAllByQuote(Long id){
        Quote quote = quoteRepository.findById(id).orElseThrow(QuoteNotFoundException::new);
        return voteRepository.findAllByQuoteOrderByDateOfVoting(quote)
                .stream()
                .map(vote -> Converter.convertToVoteDTO(vote))
                .collect(Collectors.toList());
    }
}
