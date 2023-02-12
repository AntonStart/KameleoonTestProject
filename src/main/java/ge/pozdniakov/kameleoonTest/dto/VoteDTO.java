package ge.pozdniakov.kameleoonTest.dto;

import ge.pozdniakov.kameleoonTest.models.Quote;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class VoteDTO {

    private Long id;

    private Long currentVotes;

    private LocalDateTime dateOfVoting;

    private Long quoteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurrentVotes() {
        return currentVotes;
    }

    public void setCurrentVotes(Long currentVotes) {
        this.currentVotes = currentVotes;
    }

    public LocalDateTime getDateOfVoting() {
        return dateOfVoting;
    }

    public void setDateOfVoting(LocalDateTime dateOfVoting) {
        this.dateOfVoting = dateOfVoting;
    }

    public Long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
    }
}
