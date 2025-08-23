CREATE TABLE IF NOT EXISTS Cigar (
    cigar_id INT NOT NULL,
    cigar_name VARCHAR(250) NOT NULL,
    factory_name VARCHAR(250) NOT NULL,
    wrapper_type VARCHAR(250) NOT NULL,
    wrapper_country VARCHAR(250) NOT NULL,
    binder_country VARCHAR(250) NOT NULL,
    filler_country VARCHAR(250) NOT NULL,
    PRIMARY KEY (cigar_id)
    );