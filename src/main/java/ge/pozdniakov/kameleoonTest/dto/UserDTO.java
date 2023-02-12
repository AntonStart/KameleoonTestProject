package ge.pozdniakov.kameleoonTest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class UserDTO {

    @NotEmpty(message = "Username should not be Empty")
    private String username;

    @NotEmpty(message = "Password Should not be Empty")
    private String password;

    @Email(message = "This is an Email, you make mistake")
    @NotEmpty(message = "Email Should not be Empty")
    private String email;

    private List<QuoteDTO> quotes;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<QuoteDTO> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<QuoteDTO> quotes) {
        this.quotes = quotes;
    }
}
