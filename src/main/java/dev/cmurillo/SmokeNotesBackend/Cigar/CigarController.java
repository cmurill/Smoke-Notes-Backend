package dev.cmurillo.SmokeNotesBackend.Cigar;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cigars/")
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

    @GetMapping("{id}/")
    Cigar findById(@PathVariable String id) {
        Optional<Cigar> cigar = cigarRepository.findByCigarId(id);
        if (cigar.isEmpty()) {
            throw new CigarNotFoundException("Cigar with id: " + id + " , could not be found");
        }
        return cigar.get();
    }

    //post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Cigar cigar) {
        cigarRepository.createCigar(cigar);
    }

    //put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("{id}/")
    void update(@PathVariable String id, @RequestBody Cigar cigar) {
        cigarRepository.updateCigar(id, cigar);
    }

    //delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}/")
    void delete(@PathVariable String id) {
        cigarRepository.deleteCigarById(id);
    }
}
