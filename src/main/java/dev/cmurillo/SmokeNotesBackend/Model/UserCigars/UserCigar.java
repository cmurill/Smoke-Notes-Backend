package dev.cmurillo.SmokeNotesBackend.Model.UserCigars;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.cmurillo.SmokeNotesBackend.Exceptions.InvalidUserCigarRatingException;
import dev.cmurillo.SmokeNotesBackend.Model.Cigars.Cigar;
import dev.cmurillo.SmokeNotesBackend.Model.Users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_cigars")
public class UserCigar {

    @EmbeddedId
    @JsonIgnore
    private UserCigarId ucid;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("cigarId")
    @JoinColumn(name = "cigar_id", nullable = false)
    private Cigar cigar;

    private int userRating;

    @Column(nullable = false)
    private LocalDateTime dateAdded;

    protected UserCigar() {}

    public UserCigar(User user, Cigar cigar, int userRating) {
        this.user = user;
        this.cigar = cigar;
        this.ucid = new UserCigarId(user.getUserId(), cigar.getCigarId());
        if (!(userRating >= 1 && userRating <= 10)) {
            throw new InvalidUserCigarRatingException("Invalid rating: value must be between 1 and 10");
        }
        this.dateAdded = LocalDateTime.now();
    }

    @JsonProperty("userId")
    public String getUserId() {
        return ucid != null ? ucid.getUserId() : null;
    }

    public Cigar getCigar() {
        return cigar;
    }

    public int getUserRating() {
        return userRating;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public User getUser() {
        return user;
    }
}
