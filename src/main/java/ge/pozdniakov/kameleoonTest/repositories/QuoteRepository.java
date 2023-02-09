package ge.pozdniakov.kameleoonTest.repositories;

import ge.pozdniakov.kameleoonTest.models.Quote;
import ge.pozdniakov.kameleoonTest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    List<Quote> findAllByUser(User user);

}
