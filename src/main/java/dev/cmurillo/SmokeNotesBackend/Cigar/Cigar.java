package dev.cmurillo.SmokeNotesBackend.Cigar;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;
import java.util.UUID;

public record Cigar(
        @Id
        String cigarId,
        // should not be null
        String cigarName,
        // should not be null
        String factoryName,
        // should not be null
        WrapperType wrapperType,
        OriginCountry wrapperCountry,
        OriginCountry binderCountry,
        OriginCountry fillerCountry,
        // should be between 1-10 or null if not rated
        Integer userRating,
        @Version
        Integer version
) {

    public Cigar {
        if (cigarId == null ||  cigarId.isBlank()) {
            cigarId = UUID.randomUUID().toString();
        }
    }
}
