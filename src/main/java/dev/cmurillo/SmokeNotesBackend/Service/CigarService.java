package dev.cmurillo.SmokeNotesBackend.Service;

import dev.cmurillo.SmokeNotesBackend.Exceptions.CigarNotFoundException;
import dev.cmurillo.SmokeNotesBackend.Exceptions.ExistingCigarException;
import dev.cmurillo.SmokeNotesBackend.Model.Cigars.Cigar;
import dev.cmurillo.SmokeNotesBackend.Repository.CigarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CigarService {

    private final CigarRepository cigarRepository;

    public CigarService(CigarRepository cigarRepository) {
        this.cigarRepository = cigarRepository;
    }

    public List<Cigar> getAllCigars() {
        return cigarRepository.findAll();
    }

    public Cigar getCigarById(String id) {
        return checkCigarById(id);
    }

    // Prior to creating a cigar, this method checks to see if there is a cigar that already exists with all of the same fields
    public void createCigar(Cigar cigar) {
        Optional<Cigar> existing = cigarRepository.findExistingCigar(
                cigar.getCigarName(),
                cigar.getFactoryName(),
                cigar.getWrapperType(),
                cigar.getWrapperCountry(),
                cigar.getBinderCountry(),
                cigar.getFillerCountry()
        );

        if (existing.isPresent()) {
            throw new ExistingCigarException("Error: This cigar already exists in the database.");
        }
        cigarRepository.save(cigar);
    }

    public void updateCigar(Cigar updatedCigar, String id) {
        Cigar currentCigar = checkCigarById(id);
        Cigar newCigar = new Cigar(
                currentCigar.getCigarId(),
                updatedCigar.getCigarName(),
                updatedCigar.getFactoryName(),
                updatedCigar.getWrapperType(),
                updatedCigar.getWrapperCountry(),
                updatedCigar.getBinderCountry(),
                updatedCigar.getFillerCountry(),
                currentCigar.getVersion()
        );
        cigarRepository.save(newCigar);
    }

    public void deleteCigar(String id) {
        Cigar currentCigar = checkCigarById(id);
        cigarRepository.delete(currentCigar);
    }

    private Cigar checkCigarById(String id) {
        Optional<Cigar> cigar = cigarRepository.findById(id);
        if (cigar.isEmpty()) {
            throw new CigarNotFoundException("A cigar with the id: " + id + " was not found.");
        }
        return cigar.get();
    }
}
