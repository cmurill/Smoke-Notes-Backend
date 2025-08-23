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

    @GetMapping("")
    List<Cigar> findAll() {
        return cigarRepository.findAll();
    }

    @GetMapping("{id}/")
    Cigar findById(@PathVariable Integer id) {
        Optional<Cigar> cigar = cigarRepository.findByCigarId(id);
        if (cigar.isEmpty()) {
            throw new CigarNotFoundException("Cigar with id: " + id + " , could not be found");
        }
        return cigar.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Cigar cigar) {
        cigarRepository.createCigar(cigar);
    }
}
