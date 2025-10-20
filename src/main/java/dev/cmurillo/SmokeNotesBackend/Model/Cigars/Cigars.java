package dev.cmurillo.SmokeNotesBackend.Model.Cigars;

import java.util.List;

public class Cigars {

    private List<Cigar> cigars;

    public Cigars() {}

    public Cigars(List<Cigar> cigars) {
        this.cigars = cigars;
    }

    public List<Cigar> getCigars() {
        return cigars;
    }
}
