package ge.pozdniakov.kameleoonTest.dto;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public class QuoteDTO {

    private Long id;

    @NotEmpty(message = "Your quote should not be Empty")
    private String content;

    private Long currentVotes;
    
    @NotEmpty(message = "Your quote should not be Empty")
    private String username;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCurrentVotes() {
        return currentVotes;
    }

    public void setCurrentVotes(Long currentVotes) {
        this.currentVotes = currentVotes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
