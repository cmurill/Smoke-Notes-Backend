package dev.cmurillo.SmokeNotesBackend.Cigar;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CigarRepository extends ListCrudRepository<Cigar, String> {

    List<Cigar> findAllByWrapperType(String wrapperType);
    List<Cigar> findByUserRating(Integer x);
    @Query("""
        SELECT * FROM cigar 
        WHERE cigar_name = :cigarName 
        AND factory_name = :factoryName 
        AND wrapper_type = :wrapperType 
        AND wrapper_country = :wrapperCountry 
        AND binder_country = :binderCountry 
        AND filler_country = :fillerCountry
    """)
    Optional<Cigar> findExistingCigar(
            @Param("cigarName") String cigarName,
            @Param("factoryName") String factoryName,
            @Param("wrapperType") WrapperType wrapperType,
            @Param("wrapperCountry") OriginCountry wrapperCountry,
            @Param("binderCountry") OriginCountry binderCountry,
            @Param("fillerCountry") OriginCountry fillerCountry
    );
}