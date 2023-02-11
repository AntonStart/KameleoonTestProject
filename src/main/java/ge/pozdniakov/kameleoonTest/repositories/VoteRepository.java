package ge.pozdniakov.kameleoonTest.repositories;

import ge.pozdniakov.kameleoonTest.models.Quote;
import ge.pozdniakov.kameleoonTest.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {
    List<Vote> findAllByQuoteOrderByDateOfVoting(Quote quote);
    Vote findFirstByQuoteOrderByDateOfVotingDesc(Quote quote);
}
