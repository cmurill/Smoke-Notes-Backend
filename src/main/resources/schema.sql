CREATE TABLE IF NOT EXISTS Cigar (
    cigar_id VARCHAR(36) NOT NULL,
    cigar_name VARCHAR(250) NOT NULL,
    factory_name VARCHAR(250) NOT NULL,
    wrapper_type VARCHAR(250) NOT NULL,
    wrapper_country VARCHAR(250) NOT NULL,
    binder_country VARCHAR(250) NOT NULL,
    filler_country VARCHAR(250) NOT NULL,
    user_rating INT,
    version INT,
    PRIMARY KEY (cigar_id)
    );

CREATE TABLE IF NOT EXISTS users (
    user_id VARCHAR(36) NOT NULL,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL,
    user_email VARCHAR(250) NOT NULL,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    version INT,
    PRIMARY KEY (user_id)
);
