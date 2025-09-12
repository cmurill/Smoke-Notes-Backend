package dev.cmurillo.SmokeNotesBackend.Cigar;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.lang.IllegalArgumentException;

@RestController
@RequestMapping("/api/cigars")
public class CigarController {

    private final CigarRepository cigarRepository;

    public CigarController(CigarRepository cigarRepository) {
        this.cigarRepository = cigarRepository;
    }

    //get
    @GetMapping("")
    List<Cigar> findAll() {
        return cigarRepository.findAll();
    }

    @GetMapping("/{id}")
    Cigar findById(@PathVariable String id) {
        Optional<Cigar> cigar = cigarRepository.findById(id);
        if (cigar.isEmpty()) {
            throw new CigarNotFoundException("Cigar with id: " + id + " could not be found");
        }
        return cigar.get();
    }

    //post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Cigar cigar) {
        Optional<Cigar> existingCigar = cigarRepository.findByCigarNameAndFactoryNameAndWrapperTypeAndWrapperCountryAndBinderCountryAndFillerCountry(
            cigar.cigarName(),
            cigar.factoryName(),
            cigar.wrapperType(),
            cigar.wrapperCountry(),
            cigar.binderCountry(),
            cigar.fillerCountry());
        
        if (existingCigar.isPresent()) {
            throw new IllegalArgumentException("This cigar already exists in the database.");
        }
        cigarRepository.save(cigar);
    }

    //put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Cigar newCigar, @PathVariable String id) {
        Cigar existingCigar = findById(id);

        Cigar updatedCigar = new Cigar(existingCigar.cigarId(),newCigar.cigarName(),newCigar.factoryName(),newCigar.wrapperType(),newCigar.wrapperCountry(),newCigar.binderCountry(),newCigar.fillerCountry(),newCigar.userRating(),existingCigar.version());
        cigarRepository.save(updatedCigar);
    }

    //delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        Optional<Cigar> cigar = cigarRepository.findById(id);
        if (cigar.isEmpty()) {
            throw new CigarNotFoundException("A cigar with the id: " + id + " could not be found or deleted.");
        }
        cigarRepository.deleteById(id);
    }

    @GetMapping("/wrapper/{wrapper}")
    List<Cigar> findByWrapper(@PathVariable String wrapper) {
        List<Cigar> cigarsByWrapper = cigarRepository.findAllByWrapperType(wrapper.toUpperCase());
    
        if (cigarsByWrapper.isEmpty()) {
            throw new CigarNotFoundException("Cigars with the wrapper type " + wrapper.toUpperCase() + ", could not be found.");
        } 
        return cigarsByWrapper;    
    }

    @GetMapping("/ratings/{rating}")
    List<Cigar> findByRating(@PathVariable Integer rating) {
        List<Cigar> cigarsByRating = cigarRepository.findByUserRating(rating);

        if (cigarsByRating.isEmpty()) {
            throw new CigarNotFoundException("Cigars with a user rating of " + rating + ", could not be found.");
        }
        return cigarsByRating;
    }
}