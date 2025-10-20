package dev.cmurillo.SmokeNotesBackend.Model.Cigars;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Cigar {

    @Id
    private String cigarId;

    private String cigarName;

    private String factoryName;

    @Enumerated(EnumType.STRING)
    private WrapperType wrapperType;

    @Enumerated(EnumType.STRING)
    private OriginCountry wrapperCountry;

    @Enumerated(EnumType.STRING)
    private OriginCountry binderCountry;

    @Enumerated(EnumType.STRING)
    private OriginCountry fillerCountry;

    @Version
    private Integer version;

    protected Cigar() {}

    public Cigar(
            String cigarId,
            String cigarName,
            String factoryName,
            WrapperType wrapperType,
            OriginCountry wrapperCountry,
            OriginCountry binderCountry,
            OriginCountry fillerCountry,
            Integer version) {

        this.cigarId = cigarId;
        this.cigarName = cigarName;
        this.factoryName = factoryName;
        this.wrapperType = wrapperType;
        this.wrapperCountry = wrapperCountry;
        this.binderCountry = binderCountry;
        this.fillerCountry = fillerCountry;
        this.version = version;
    }

    public String getCigarId() {
        return cigarId;
    }

    public String getCigarName() {
        return cigarName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public WrapperType getWrapperType() {
        return wrapperType;
    }

    public OriginCountry getWrapperCountry() {
        return wrapperCountry;
    }

    public OriginCountry getBinderCountry() {
        return binderCountry;
    }

    public OriginCountry getFillerCountry() {
        return fillerCountry;
    }

    public Integer getVersion() {
        return version;
    }

    @PrePersist
    public void handleCigarId() {
        if (this.cigarId == null || this.cigarId.isBlank()) {
            this.cigarId = UUID.randomUUID().toString();
        }
    }
}
