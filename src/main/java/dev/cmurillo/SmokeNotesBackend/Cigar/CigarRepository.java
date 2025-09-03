package dev.cmurillo.SmokeNotesBackend.Cigar;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface CigarRepository extends ListCrudRepository<Cigar, String> {

    List<Cigar> findAllByWrapperType(String wrapperType);
}
