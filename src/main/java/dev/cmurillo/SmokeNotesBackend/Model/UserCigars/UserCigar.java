package dev.cmurillo.SmokeNotesBackend.Model.UserCigars;

import dev.cmurillo.SmokeNotesBackend.Model.Cigars.Cigar;
import dev.cmurillo.SmokeNotesBackend.Model.Users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class UserCigar {

    @EmbeddedId
    private UserCigarId ucigarId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cigarId")
    @JoinColumn(name = "cigar_id", nullable = false)
    private Cigar cigar;

    private LocalDateTime dateAdded = LocalDateTime.now();

    protected UserCigar() {}

    public UserCigar(User user, Cigar cigar) {
        this.user = user;
        this.cigar = cigar;
        this.ucigarId = new UserCigarId(user.getUserId(), cigar.getCigarId());
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }
}
