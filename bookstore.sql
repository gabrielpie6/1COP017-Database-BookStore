CREATE SCHEMA bookstore;

CREATE TABLE bookstore.product (
    cod    INT,
    price  DECIMAL(10, 2),
    amount INT ,

    CONSTRAINT pk_product PRIMARY KEY (cod),
    CONSTRAINT amount_positive CHECK (amount >= 0)
);

CREATE TABLE bookstore.publisher (
    fname VARCHAR(100),
    city  VARCHAR(50),
    
    CONSTRAINT pk_publisher PRIMARY KEY (fname)
);

CREATE TABLE bookstore.genre (
    fname    VARCHAR(50),
    discount REAL,

    CONSTRAINT pk_genre PRIMARY KEY (fname)
);

CREATE TABLE bookstore.book (
    fk_product_cod     INT,
    ISBN               CHAR(13) UNIQUE,
    title              VARCHAR(100),
    bedition           INT,
    blanguage          VARCHAR(50),
    pages              INT,
    publication_year   INT,
    fk_publisher_fname VARCHAR(100) NOT NULL,
    fk_genre_fname     VARCHAR(50) NOT NULL,

    CONSTRAINT pk_book      PRIMARY KEY (fk_product_cod),
    CONSTRAINT fk_product   FOREIGN KEY (fk_product_cod)     REFERENCES bookstore.product(cod),
    CONSTRAINT fk_publisher FOREIGN KEY (fk_publisher_fname) REFERENCES bookstore.publisher(fname),
    CONSTRAINT fk_genre     FOREIGN KEY (fk_genre_fname)     REFERENCES bookstore.genre(fname),

    CONSTRAINT isbn_only_numbers      CHECK (ISBN ~ '^[0-9]{1,13}$'),
    CONSTRAINT publication_year_valid CHECK (publication_year > 0 AND publication_year <= EXTRACT(YEAR FROM CURRENT_DATE))
);

CREATE TABLE bookstore.book_author (
    fk_book_cod INT,
    author_name VARCHAR(100),

    CONSTRAINT pk_book_author PRIMARY KEY (fk_book_cod, author_name),
    CONSTRAINT fk_book FOREIGN KEY (fk_book_cod) REFERENCES bookstore.book(fk_product_cod)
);

CREATE TABLE bookstore.theme (
    fname    VARCHAR(50),
    discount REAL,

    CONSTRAINT pk_theme PRIMARY KEY (fname)
);

CREATE TABLE bookstore.magazine (
    fk_product_cod     INT,
    ISSN               CHAR(8)      UNIQUE,
    fname              VARCHAR(100),
    pages              INT,
    publication_date   DATE,
    fk_publisher_fname VARCHAR(100) NOT NULL,
    fk_theme_fname     VARCHAR(50)  NOT NULL,

    CONSTRAINT pk_magazine  PRIMARY KEY (fk_product_cod),
    CONSTRAINT fk_product   FOREIGN KEY (fk_product_cod)     REFERENCES bookstore.product(cod),
    CONSTRAINT fk_publisher FOREIGN KEY (fk_publisher_fname) REFERENCES bookstore.publisher(fname),

    CONSTRAINT issn_only_numbers CHECK (ISSN ~ '^[0-9]{8}$')
);

CREATE TABLE bookstore.pagemark (
    fk_product_cod INT,
    color          VARCHAR(20),

    CONSTRAINT pk_pagemark PRIMARY KEY (fk_product_cod),
    CONSTRAINT fk_product  FOREIGN KEY (fk_product_cod) REFERENCES bookstore.product(cod)
);

CREATE TABLE bookstore.customer (
    cpf            CHAR(11),
    fname          VARCHAR(100),
    lname          VARCHAR(100),
    CEP            CHAR(8),
    address_street VARCHAR(100),
    address_number INT,

    CONSTRAINT pk_customer      PRIMARY KEY (cpf),
    CONSTRAINT cpf_only_numbers CHECK (cpf ~ '^[0-9]{11}$'),
    CONSTRAINT cep_only_numbers CHECK (CEP ~ '^[0-9]{8}$')
);

CREATE TABLE bookstore.purchase (
    invoice_number  INT,
    date_hour       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fk_customer_cpf CHAR(11) NOT NULL,

    CONSTRAINT pk_purchase PRIMARY KEY (invoice_number),
    CONSTRAINT fk_customer FOREIGN KEY (fk_customer_cpf) REFERENCES bookstore.customer(cpf)
);

CREATE TABLE bookstore.purchase_product (
    fk_invoice_number INT,
    fk_product_cod    INT,
    checkout_amount   INT,

    CONSTRAINT pk_purchase_product PRIMARY KEY (fk_invoice_number, fk_product_cod),
    CONSTRAINT fk_purchase FOREIGN KEY (fk_invoice_number) REFERENCES bookstore.purchase(invoice_number),
    CONSTRAINT fk_product  FOREIGN KEY (fk_product_cod)    REFERENCES bookstore.product(cod)
);