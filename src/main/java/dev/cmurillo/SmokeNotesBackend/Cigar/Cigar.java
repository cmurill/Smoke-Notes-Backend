package dev.cmurillo.SmokeNotesBackend.Cigar;

public record Cigar(
        Integer cigarId,
        String cigarName,
        String factoryName,
        WrapperType wrapperType,
        OriginCountry wrapperCountry,
        OriginCountry binderCountry,
        OriginCountry fillerCountry
) {
}
