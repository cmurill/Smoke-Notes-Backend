package dev.cmurillo.SmokeNotesBackend.Cigar;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CigarRepository extends ListCrudRepository<Cigar, String> {

    List<Cigar> findAllByWrapperType(String wrapperType);
    List<Cigar> findByUserRating(Integer x);
    Optional<Cigar> findByCigarNameAndFactoryNameAndWrapperTypeAndWrapperCountryAndBinderCountryAndFillerCountry(String cigarName, String factoryName, WrapperType wrapperType, OriginCountry wrapperCountry, OriginCountry binderCountry, OriginCountry fillerCountry);
}