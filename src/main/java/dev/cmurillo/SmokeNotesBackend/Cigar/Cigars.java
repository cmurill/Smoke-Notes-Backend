package dev.cmurillo.SmokeNotesBackend.Cigar;


import java.util.List;

// A record to hold the list of cigars that we need when reading in data from the JSON file
public record Cigars(List<Cigar> cigars) {
}
