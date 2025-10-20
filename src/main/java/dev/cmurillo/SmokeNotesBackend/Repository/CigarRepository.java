package dev.cmurillo.SmokeNotesBackend.Repository;

import dev.cmurillo.SmokeNotesBackend.Model.Cigars.Cigar;
import dev.cmurillo.SmokeNotesBackend.Model.Cigars.OriginCountry;
import dev.cmurillo.SmokeNotesBackend.Model.Cigars.WrapperType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CigarRepository extends JpaRepository<Cigar,String> {

    // Custom query using JPQL that returns an optional for a cigar with the same fields except id and version
    @Query("""
        SELECT c FROM Cigar c
        WHERE c.cigarName = :cigarName
        AND c.factoryName = :factoryName
        AND c.wrapperType = :wrapperType
        AND c.wrapperCountry = :wrapperCountry
        AND c.binderCountry = :binderCountry
        AND c.fillerCountry = :fillerCountry
        """)
    Optional<Cigar> findExistingCigar(
            @Param("cigarName") String cigarName,
            @Param("factoryName") String factoryName,
            @Param("wrapperType") WrapperType wrapperType,
            @Param("wrapperCountry")OriginCountry wrapperCountry,
            @Param("binderCountry") OriginCountry binderCountry,
            @Param("fillerCountry") OriginCountry fillerCountry);
}
