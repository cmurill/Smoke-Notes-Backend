package dev.cmurillo.SmokeNotesBackend.Cigar;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;
import java.util.UUID;

public record Cigar(
        @Id
        String cigarId,
        String cigarName,
        String factoryName,
        WrapperType wrapperType,
        OriginCountry wrapperCountry,
        OriginCountry binderCountry,
        OriginCountry fillerCountry,
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
