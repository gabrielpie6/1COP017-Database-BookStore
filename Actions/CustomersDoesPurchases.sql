INSERT INTO bookstore.purchase (invoice_number, fk_customer_cpf) VALUES (1001, '12345678901');
INSERT INTO bookstore.purchase_product(fk_invoice_number, fk_product_cod, checkout_amount) VALUES (1001, 1, 5);
UPDATE bookstore.product
SET amount = 10 - 5
WHERE cod = 1;
INSERT INTO bookstore.purchase_product(fk_invoice_number, fk_product_cod, checkout_amount) VALUES (1001, 6, 10);
UPDATE bookstore.product
SET amount = 60 - 10
WHERE cod = 6;


INSERT INTO bookstore.purchase (invoice_number, fk_customer_cpf) VALUES (1002, '12345678902');
INSERT INTO bookstore.purchase_product(fk_invoice_number, fk_product_cod, checkout_amount) VALUES (1002, 1, 5);
UPDATE bookstore.product
SET amount = 10 - 5 - 5
WHERE cod = 1;
INSERT INTO bookstore.purchase_product(fk_invoice_number, fk_product_cod, checkout_amount) VALUES (1002, 14, 10);
UPDATE bookstore.product
SET amount = 20 - 10
WHERE cod = 14;
INSERT INTO bookstore.purchase_product(fk_invoice_number, fk_product_cod, checkout_amount) VALUES (1002, 22, 20);
UPDATE bookstore.product
SET amount = 100 - 20
WHERE cod = 22;


INSERT INTO bookstore.purchase (invoice_number, fk_customer_cpf) VALUES (1003, '12345678901');
INSERT INTO bookstore.purchase_product(fk_invoice_number, fk_product_cod, checkout_amount) VALUES (1003, 23, 2);
UPDATE bookstore.product
SET amount = 100 - 2
WHERE cod = 23;
INSERT INTO bookstore.purchase_product(fk_invoice_number, fk_product_cod, checkout_amount) VALUES (1003, 22, 4);
UPDATE bookstore.product
SET amount = 100 - 20 - 4
WHERE cod = 22;