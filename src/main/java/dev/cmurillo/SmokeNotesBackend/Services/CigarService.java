package dev.cmurillo.SmokeNotesBackend.Services;

import dev.cmurillo.SmokeNotesBackend.Cigar.Cigar;
import dev.cmurillo.SmokeNotesBackend.Cigar.CigarNotFoundException;
import dev.cmurillo.SmokeNotesBackend.Cigar.CigarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CigarService {

    private final CigarRepository cigarRepository;

    public CigarService(CigarRepository cigarRepository) {
        this.cigarRepository = cigarRepository;
    }

    public List<Cigar> findAllCigars() {
        return cigarRepository.findAll();
    }

    public Cigar findCigarById(String id) {
        return this.checkCigarById(id);
    }

    public void createCigar(Cigar cigar) {
        Optional<Cigar> existingCigar = cigarRepository.findExistingCigar(
                cigar.cigarName(),
                cigar.factoryName(),
                cigar.wrapperType(),
                cigar.wrapperCountry(),
                cigar.binderCountry(),
                cigar.fillerCountry()
        );

        if (existingCigar.isPresent()) {
            throw new IllegalArgumentException("This cigar already exists in the database.");
        }
        cigarRepository.save(cigar);
    }

    public void updateCigar(Cigar updatedCigar, String id) {
        Cigar currentCigar = this.checkCigarById(id);
        Cigar newCigar = new Cigar(currentCigar.cigarId(), updatedCigar.cigarName(), updatedCigar.factoryName(), updatedCigar.wrapperType(), updatedCigar.wrapperCountry(), updatedCigar.binderCountry(), updatedCigar.fillerCountry(), updatedCigar.userRating(), currentCigar.version());
        cigarRepository.save(newCigar);
    }

    public void deleteCigar(String id) {
        checkCigarById(id);
        cigarRepository.deleteById(id);
    }

    public List<Cigar> findCigarsByWrapper(String wrapper) {
        List<Cigar> cigarsByWrapper = cigarRepository.findAllByWrapperType(wrapper.toUpperCase());

        if (cigarsByWrapper.isEmpty()) {
            throw new CigarNotFoundException("Cigars with the wrapper type " + wrapper.toUpperCase() + ", could not be found.");
        }
        return cigarsByWrapper;
    }

    public List<Cigar> findCigarsByRating(Integer rating) {
        List<Cigar> cigarsByRating = cigarRepository.findByUserRating(rating);

        if (cigarsByRating.isEmpty()) {
            throw new CigarNotFoundException("Cigars with a user rating of " + rating + ", could not be found.");
        }
        return cigarsByRating;
    }

    private Cigar checkCigarById(String id) {
        Optional<Cigar> cigar = cigarRepository.findById(id);
        if (cigar.isEmpty()) {
            throw new CigarNotFoundException("Cigar with the id: " + id + " was not found.");
        }
        return cigar.get();
    }
}
