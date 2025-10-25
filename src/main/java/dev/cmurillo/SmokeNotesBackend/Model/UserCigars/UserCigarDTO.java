package dev.cmurillo.SmokeNotesBackend.Model.UserCigars;

import dev.cmurillo.SmokeNotesBackend.Model.Cigars.Cigar;

import java.time.LocalDateTime;
import java.util.List;

public record UserCigarDTO(String userId, List<UserCigarItem> cigars) {
    public record UserCigarItem(Cigar cigar, LocalDateTime dateAdded){}
}
