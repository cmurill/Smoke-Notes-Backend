package dev.cmurillo.SmokeNotesBackend.Cigar;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
            throw new CigarNotFoundException("Cigar with id: " + id + " , could not be found");
        }
        return cigar.get();
    }

    //post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Cigar cigar) {
        cigarRepository.save(cigar);
    }

    //put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Cigar cigar, @PathVariable String id) {
        cigarRepository.save(cigar);
    }

    //delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        cigarRepository.delete(cigarRepository.findById(id).get());
    }

    @GetMapping("/wrapper/{wrapper}")
    List<Cigar> findByWrapper(@PathVariable String wrapper) {
        return cigarRepository.findAllByWrapperType(wrapper.toUpperCase());
    }
}
