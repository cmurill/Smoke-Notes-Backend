package dev.cmurillo.SmokeNotesBackend.Service;

import dev.cmurillo.SmokeNotesBackend.Exceptions.CigarNotFoundException;
import dev.cmurillo.SmokeNotesBackend.Exceptions.ExistingCigarException;
import dev.cmurillo.SmokeNotesBackend.Model.Cigars.Cigar;
import dev.cmurillo.SmokeNotesBackend.Repository.CigarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CigarService {

    private final CigarRepository cigarRepository;
    private static final Logger log = LoggerFactory.getLogger(CigarService.class);

    public CigarService(CigarRepository cigarRepository) {
        this.cigarRepository = cigarRepository;
    }

    public List<Cigar> getAllCigars() {
        log.info(getClass() + ": Getting all cigars from the database");
        List<Cigar> cigars = cigarRepository.findAll();
        log.info(getClass() + ": Retrieved {} cigars from the database", cigars.size());
        return cigars;
    }

    public Cigar getCigarById(String id) {
        log.info(getClass() + ": Searching for a cigar with id {}", id);
        return checkCigarById(id);
    }

    // Prior to creating a cigar, this method checks to see if there is a cigar that already exists with all the same fields
    public void createCigar(Cigar cigar) {
        log.info(getClass() + ": Attempting to create a new cigar");
        log.info(getClass() + ": Checking for existing cigar with the same fields");
        Optional<Cigar> existing = cigarRepository.findExistingCigar(
                cigar.getCigarName(),
                cigar.getFactoryName(),
                cigar.getWrapperType(),
                cigar.getWrapperCountry(),
                cigar.getBinderCountry(),
                cigar.getFillerCountry()
        );

        if (existing.isPresent()) {
            log.warn(getClass() + ": Error creating a new cigar because a cigar with the same fields already exists in the database");
            throw new ExistingCigarException("Error: This cigar already exists in the database.");
        }
        cigarRepository.save(cigar);
        log.info(getClass() + ": Successfully created a new cigar = {}", cigar);
    }

    public void updateCigar(Cigar updatedCigar, String id) {
        log.info(getClass() + ": Attempting to update a cigar with id {}", id);
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
        log.info(getClass() + ": Successfully updated the cigar to {}", newCigar);
    }

    public void deleteCigar(String id) {
        log.info(getClass() + ": Attempting to delete a cigar with id {}", id);
        Cigar currentCigar = checkCigarById(id);
        cigarRepository.delete(currentCigar);
        log.info(getClass() + ": Successfully deleted cigar {}", currentCigar);
    }

    private Cigar checkCigarById(String id) {
        Optional<Cigar> cigar = cigarRepository.findById(id);
        if (cigar.isEmpty()) {
            log.warn(getClass() + ": Cigar not found with id={}", id);
            throw new CigarNotFoundException("A cigar with the id: " + id + " was not found.");
        }
        log.info(getClass() + ": Found cigar with id={}", id);
        return cigar.get();
    }
}
