package ge.pozdniakov.kameleoonTest.services;

import ge.pozdniakov.kameleoonTest.models.Quote;
import ge.pozdniakov.kameleoonTest.models.User;
import ge.pozdniakov.kameleoonTest.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional(readOnly = true)
public class QuoteService {

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public List<Quote> findAllByUser(User user){
        return quoteRepository.findAllByUser(user);
    }

    public List<Quote> findAll(){
        return quoteRepository.findAll();
    }

    @Transactional
    public void saveQuote(Quote quote){
        quoteRepository.save(quote);
    }

    @Transactional
    public void deleteQuote(Quote quote){
        quoteRepository.delete(quote);
    }

    public Quote showRandom(){
        int randomId = ThreadLocalRandom.current().nextInt(quoteRepository.findAll().size());
        return quoteRepository
                .findAll()
                .get(randomId);
    }
}
