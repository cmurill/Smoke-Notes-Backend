package dev.cmurillo.SmokeNotesBackend.Model.UserCigars;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserCigarId implements Serializable {

    private String userId;

    private String cigarId;

    protected UserCigarId() {}

    public UserCigarId(String userId, String cigarId) {
        this.userId = userId;
        this.cigarId = cigarId;
    }

    public String getUserId() {return userId;}

    public String getCigarId() {return cigarId;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCigarId other)) return false;
        return Objects.equals(userId, other.userId) && Objects.equals(cigarId, other.cigarId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, cigarId);
    }

}
