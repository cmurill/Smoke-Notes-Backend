package dev.cmurillo.SmokeNotesBackend.Cigar;

import java.time.LocalDateTime;
import java.util.UUID;

public record Cigar(
        String cigarId,
        String cigarName,
        String factoryName,
        WrapperType wrapperType,
        OriginCountry wrapperCountry,
        OriginCountry binderCountry,
        OriginCountry fillerCountry
) {

    public Cigar {
        if (cigarId == null ||  cigarId.isBlank()) {
            cigarId = UUID.randomUUID().toString();
        }
    }
}
