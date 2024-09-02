-- PRODUCTS
-- Books
INSERT INTO bookstore.product (cod, price, amount) VALUES (1, 100.00, 10);
INSERT INTO bookstore.product (cod, price, amount) VALUES (2,  90.00, 20);
INSERT INTO bookstore.product (cod, price, amount) VALUES (3,  80.00, 30);
INSERT INTO bookstore.product (cod, price, amount) VALUES (4,  70.00, 40);
INSERT INTO bookstore.product (cod, price, amount) VALUES (5,  60.00, 50);
INSERT INTO bookstore.product (cod, price, amount) VALUES (6,  50.00, 60);
INSERT INTO bookstore.product (cod, price, amount) VALUES (7,  40.00, 70);
INSERT INTO bookstore.product (cod, price, amount) VALUES (8,  30.00, 80);
INSERT INTO bookstore.product (cod, price, amount) VALUES (9,  20.00, 90);
INSERT INTO bookstore.product (cod, price, amount) VALUES (10, 10.00, 100);
INSERT INTO bookstore.product (cod, price, amount) VALUES (11,  9.00, 110);
INSERT INTO bookstore.product (cod, price, amount) VALUES (12,  8.00, 120);

-- Magazines
INSERT INTO bookstore.product (cod, price, amount) VALUES (13,  55.00, 10);
INSERT INTO bookstore.product (cod, price, amount) VALUES (14,  50.00, 20);
INSERT INTO bookstore.product (cod, price, amount) VALUES (15,  45.00, 30);
INSERT INTO bookstore.product (cod, price, amount) VALUES (16,  40.00, 40);
INSERT INTO bookstore.product (cod, price, amount) VALUES (17,  35.00, 50);
INSERT INTO bookstore.product (cod, price, amount) VALUES (18,  30.00, 60);
INSERT INTO bookstore.product (cod, price, amount) VALUES (19,  25.00, 70);
INSERT INTO bookstore.product (cod, price, amount) VALUES (20,  20.00, 80);
INSERT INTO bookstore.product (cod, price, amount) VALUES (21,  15.00, 90);

-- Pagemarks
INSERT INTO bookstore.product (cod, price, amount) VALUES (22,  1.00, 100);
INSERT INTO bookstore.product (cod, price, amount) VALUES (23,  1.00, 100);
INSERT INTO bookstore.product (cod, price, amount) VALUES (24,  1.00, 100);
INSERT INTO bookstore.product (cod, price, amount) VALUES (25,  1.00, 100);
INSERT INTO bookstore.product (cod, price, amount) VALUES (26,  1.00, 100);
INSERT INTO bookstore.product (cod, price, amount) VALUES (27,  1.00, 100);







-- PUBLISHER
INSERT INTO bookstore.publisher (fname, city) VALUES ('Editora Abril',              'São Paulo');
INSERT INTO bookstore.publisher (fname, city) VALUES ('Editora Moderna',            'Londrina');
INSERT INTO bookstore.publisher (fname, city) VALUES ('Grupo Companhia das Letras', 'Rio de Janeiro');
INSERT INTO bookstore.publisher (fname, city) VALUES ('Editora Eduel',              'Londrina');
INSERT INTO bookstore.publisher (fname, city) VALUES ('Editora L&PM',               'Porto Alegre');
INSERT INTO bookstore.publisher (fname, city) VALUES ('Editora Sextante',           'Rio de Janeiro');







-- GENRE
INSERT INTO bookstore.genre (fname, discount) VALUES ('Ficção',   0.10);
INSERT INTO bookstore.genre (fname, discount) VALUES ('Terror',   0.30);
INSERT INTO bookstore.genre (fname, discount) VALUES ('Romance',  0.40);
INSERT INTO bookstore.genre (fname, discount) VALUES ('Aventura', 0.50);
INSERT INTO bookstore.genre (fname, discount) VALUES ('Infantil', 0.60);
INSERT INTO bookstore.genre (fname, discount) VALUES ('Didático', 0.70);







-- BOOK
INSERT INTO bookstore.book (fk_product_cod, ISBN, title, bedition, blanguage, pages, publication_year, fk_publisher_fname, fk_genre_fname) VALUES (1,   '9788535902773',    'O Senhor dos Anéis',           1, 'Português', 100, 1954, 'Editora Abril',                 'Ficção');
INSERT INTO bookstore.book (fk_product_cod, ISBN, title, bedition, blanguage, pages, publication_year, fk_publisher_fname, fk_genre_fname) VALUES (2,   '9789935902773',    'O Hobbit',                     9, 'Português', 200, 1937, 'Editora Moderna',               'Terror');
INSERT INTO bookstore.book (fk_product_cod, ISBN, title, bedition, blanguage, pages, publication_year, fk_publisher_fname, fk_genre_fname) VALUES (3,   '9787735902773',    'O Silmarillion',               7, 'Inglês',    300, 1977, 'Editora Moderna',               'Didático');
INSERT INTO bookstore.book (fk_product_cod, ISBN, title, bedition, blanguage, pages, publication_year, fk_publisher_fname, fk_genre_fname) VALUES (4,   '9788835902773',    'Os Filhos de Húrin',           1, 'Português', 150, 2007, 'Grupo Companhia das Letras',    'Didático');
INSERT INTO bookstore.book (fk_product_cod, ISBN, title, bedition, blanguage, pages, publication_year, fk_publisher_fname, fk_genre_fname) VALUES (5,   '9786635902773',    'Contos Inacabados',            3, 'Inglês',    205, 1980, 'Grupo Companhia das Letras',    'Romance');
INSERT INTO bookstore.book (fk_product_cod, ISBN, title, bedition, blanguage, pages, publication_year, fk_publisher_fname, fk_genre_fname) VALUES (6,   '9785535902773',    'A Queda de Gondolin',          1, 'Português', 100, 2018, 'Grupo Companhia das Letras',    'Romance');
INSERT INTO bookstore.book (fk_product_cod, ISBN, title, bedition, blanguage, pages, publication_year, fk_publisher_fname, fk_genre_fname) VALUES (7,   '9784435902773',    'Beren e Lúthien',              1, 'Português', 50,  2017, 'Editora Eduel',                 'Romance');
INSERT INTO bookstore.book (fk_product_cod, ISBN, title, bedition, blanguage, pages, publication_year, fk_publisher_fname, fk_genre_fname) VALUES (8,   '9783335902773',    'A História de Kullervo',       4, 'Alemão',    30,  2015, 'Editora L&PM',                  'Aventura');
INSERT INTO bookstore.book (fk_product_cod, ISBN, title, bedition, blanguage, pages, publication_year, fk_publisher_fname, fk_genre_fname) VALUES (9,   '9782235902773',    'A Lenda de Sigurd e Gudrún',   1, 'Inglês',    80,  2009, 'Editora L&PM',                  'Aventura');
INSERT INTO bookstore.book (fk_product_cod, ISBN, title, bedition, blanguage, pages, publication_year, fk_publisher_fname, fk_genre_fname) VALUES (10,  '9781135902773',    'O Livro dos Contos Perdidos',  1, 'Português', 400, 1983, 'Editora Abril',                 'Infantil');
INSERT INTO bookstore.book (fk_product_cod, ISBN, title, bedition, blanguage, pages, publication_year, fk_publisher_fname, fk_genre_fname) VALUES (11,  '9990035902773',    'Sapiens',                      2, 'Português', 80,  1986, 'Editora Abril',                 'Infantil');
INSERT INTO bookstore.book (fk_product_cod, ISBN, title, bedition, blanguage, pages, publication_year, fk_publisher_fname, fk_genre_fname) VALUES (12,  '8880035902773',    'Cálculo 1',                    1, 'Português', 500, 1987, 'Editora Sextante',              'Didático');









-- BOOK_AUTHOR
INSERT INTO bookstore.book_author (fk_book_cod, author_name) VALUES (1, 'J. K. Rowling');
INSERT INTO bookstore.book_author (fk_book_cod, author_name) VALUES (1, 'J. R. R. Tolkien');
INSERT INTO bookstore.book_author (fk_book_cod, author_name) VALUES (2, 'L. Tolstoy');
INSERT INTO bookstore.book_author (fk_book_cod, author_name) VALUES (3, 'D. Brown');
INSERT INTO bookstore.book_author (fk_book_cod, author_name) VALUES (4, 'S. King');
INSERT INTO bookstore.book_author (fk_book_cod, author_name) VALUES (5, 'D. Brown');
INSERT INTO bookstore.book_author (fk_book_cod, author_name) VALUES (6, 'D. Brown');
INSERT INTO bookstore.book_author (fk_book_cod, author_name) VALUES (7, 'S. King');
INSERT INTO bookstore.book_author (fk_book_cod, author_name) VALUES (7, 'L. Tolstoy');
INSERT INTO bookstore.book_author (fk_book_cod, author_name) VALUES (8, 'L. Tolstoy');
INSERT INTO bookstore.book_author (fk_book_cod, author_name) VALUES (9, 'J. R. R. Tolkien');
INSERT INTO bookstore.book_author (fk_book_cod, author_name) VALUES (10, 'J. K. Rowling');
INSERT INTO bookstore.book_author (fk_book_cod, author_name) VALUES (10, 'J. R. R. Tolkien');
INSERT INTO bookstore.book_author (fk_book_cod, author_name) VALUES (10, 'D. Brown');
INSERT INTO bookstore.book_author (fk_book_cod, author_name) VALUES (10, 'J. Austen');
INSERT INTO bookstore.book_author (fk_book_cod, author_name) VALUES (11, 'J. Austen');
INSERT INTO bookstore.book_author (fk_book_cod, author_name) VALUES (12, 'A. Christie');








-- THEME
INSERT INTO bookstore.theme (fname, discount) VALUES ('Culinária', 0.1);
INSERT INTO bookstore.theme (fname, discount) VALUES ('Costura', 0.2);
INSERT INTO bookstore.theme (fname, discount) VALUES ('Entretenimento', 0.3);
INSERT INTO bookstore.theme (fname, discount) VALUES ('Empresarial', 0.4);








-- MAGAZINE
INSERT INTO bookstore.magazine (fk_product_cod, ISSN, fname, pages, publication_date, fk_publisher_fname, fk_theme_fname) VALUES (13, '12345678', 'Revista Mão na Massa',                       50, '2020-01-01', 'Editora Abril',              'Culinária');
INSERT INTO bookstore.magazine (fk_product_cod, ISSN, fname, pages, publication_date, fk_publisher_fname, fk_theme_fname) VALUES (14, '87654321', 'Revista Remendo',                            60, '2018-01-01', 'Editora L&PM',               'Costura');
INSERT INTO bookstore.magazine (fk_product_cod, ISSN, fname, pages, publication_date, fk_publisher_fname, fk_theme_fname) VALUES (15, '87654322', 'Revista Agulha de Ponta',                    62, '2019-01-01', 'Grupo Companhia das Letras', 'Costura');
INSERT INTO bookstore.magazine (fk_product_cod, ISSN, fname, pages, publication_date, fk_publisher_fname, fk_theme_fname) VALUES (16, '12348765', 'Revista Tititi',                             70, '2023-01-01', 'Editora Sextante',           'Entretenimento');
INSERT INTO bookstore.magazine (fk_product_cod, ISSN, fname, pages, publication_date, fk_publisher_fname, fk_theme_fname) VALUES (17, '12348769', 'Revista Caras',                              70, '2023-01-01', 'Editora Moderna',            'Entretenimento');
INSERT INTO bookstore.magazine (fk_product_cod, ISSN, fname, pages, publication_date, fk_publisher_fname, fk_theme_fname) VALUES (18, '12348760', 'Revista Fama',                               20, '2023-01-01', 'Editora Moderna',            'Entretenimento');
INSERT INTO bookstore.magazine (fk_product_cod, ISSN, fname, pages, publication_date, fk_publisher_fname, fk_theme_fname) VALUES (19, '56781234', 'Revista Pequenas empresas grandes negócios', 80, '2024-01-01', 'Editora Abril',              'Empresarial');
INSERT INTO bookstore.magazine (fk_product_cod, ISSN, fname, pages, publication_date, fk_publisher_fname, fk_theme_fname) VALUES (20, '56381234', 'Revista Business Insider',                   80, '2021-01-01', 'Editora Eduel',              'Empresarial');
INSERT INTO bookstore.magazine (fk_product_cod, ISSN, fname, pages, publication_date, fk_publisher_fname, fk_theme_fname) VALUES (21, '56481234', 'Revista Em foco',                            80, '2019-01-01', 'Editora Eduel',              'Empresarial');








-- PAGEMARK
INSERT INTO bookstore.pagemark (fk_product_cod, color) VALUES (22, 'Azul');
INSERT INTO bookstore.pagemark (fk_product_cod, color) VALUES (23, 'Vermelho');
INSERT INTO bookstore.pagemark (fk_product_cod, color) VALUES (24, 'Verde');
INSERT INTO bookstore.pagemark (fk_product_cod, color) VALUES (25, 'Amarelo');
INSERT INTO bookstore.pagemark (fk_product_cod, color) VALUES (26, 'Preto');
INSERT INTO bookstore.pagemark (fk_product_cod, color) VALUES (27, 'Branco');