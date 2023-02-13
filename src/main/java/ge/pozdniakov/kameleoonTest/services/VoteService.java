package ge.pozdniakov.kameleoonTest.services;

import ge.pozdniakov.kameleoonTest.dto.VoteDTO;
import ge.pozdniakov.kameleoonTest.models.Quote;
import ge.pozdniakov.kameleoonTest.repositories.QuoteRepository;
import ge.pozdniakov.kameleoonTest.repositories.VoteRepository;
import ge.pozdniakov.kameleoonTest.util.Converter;
import ge.pozdniakov.kameleoonTest.util.KameleoonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class VoteService {

    private final VoteRepository voteRepository;
    private final QuoteRepository quoteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository, QuoteRepository quoteRepository) {
        this.voteRepository = voteRepository;
        this.quoteRepository = quoteRepository;
    }

    public List<VoteDTO> getAllByQuote(Long id){
        Quote quote = quoteRepository.findById(id).orElseThrow(KameleoonException::new);
        return voteRepository.findAllByQuoteOrderByDateOfVoting(quote)
                .stream()
                .map(Converter::convertToVoteDTO)
                .collect(Collectors.toList());
    }
}
