package dev.cmurillo.SmokeNotesBackend.Cigar;

import dev.cmurillo.SmokeNotesBackend.Services.CigarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.lang.IllegalArgumentException;

@RestController
@RequestMapping("/api/cigars")
public class CigarController {

    private final CigarService cigarService;

    public CigarController(CigarService cigarService) {
        this.cigarService = cigarService;
    }

    //get
    @GetMapping("")
    List<Cigar> findAll() {
        return cigarService.findAllCigars();
    }

    @GetMapping("/{id}")
    Cigar findById(@PathVariable String id) {
        return cigarService.findCigarById(id);
    }

    //post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Cigar cigar) {
        cigarService.createCigar(cigar);
    }

    //put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Cigar updatedCigar, @PathVariable String id) {
        cigarService.updateCigar(updatedCigar, id);
    }

    //delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        cigarService.deleteCigar(id);
    }

    @GetMapping("/wrapper/{wrapper}")
    List<Cigar> findByWrapper(@PathVariable String wrapper) {
        return cigarService.findCigarsByWrapper(wrapper);
    }

    @GetMapping("/ratings/{rating}")
    List<Cigar> findByRating(@PathVariable Integer rating) {
        return cigarService.findCigarsByRating(rating);
    }
}