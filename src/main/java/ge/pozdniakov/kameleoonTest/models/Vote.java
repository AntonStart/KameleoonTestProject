package ge.pozdniakov.kameleoonTest.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "current_votes")
    private Long currentVotes;

    @Column(name = "date_of_voting")
    private LocalDateTime dateOfVoting;

    @ManyToOne
    @JoinColumn(name = "quote_id", referencedColumnName = "id")
    private Quote quote;

    public Vote() {
    }

    public Vote(Long currentRate, LocalDateTime dateOfVoting, Quote quote) {
        this.currentVotes = currentRate;
        this.dateOfVoting = dateOfVoting;
        this.quote = quote;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurrentRate() {
        return currentVotes;
    }

    public void setCurrentRate(Long currentRate) {
        this.currentVotes = currentRate;
    }

    public LocalDateTime getDateOfVoting() {
        return dateOfVoting;
    }

    public void setDateOfVoting(LocalDateTime dateOfVoting) {
        this.dateOfVoting = dateOfVoting;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }
}
