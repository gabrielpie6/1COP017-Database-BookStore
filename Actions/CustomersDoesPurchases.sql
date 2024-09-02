INSERT INTO bookstore.purchase (invoice_number, fk_customer_cpf) VALUES (1001, '12345678901');
INSERT INTO bookstore.purchase_product(fk_invoice_number, fk_product_cod, checkout_amount) VALUES (1001, 1, 5);
INSERT INTO bookstore.purchase_product(fk_invoice_number, fk_product_cod, checkout_amount) VALUES (1001, 6, 10);


INSERT INTO bookstore.purchase (invoice_number, fk_customer_cpf) VALUES (1002, '12345678902');
INSERT INTO bookstore.purchase_product(fk_invoice_number, fk_product_cod, checkout_amount) VALUES (1002, 1, 5);
INSERT INTO bookstore.purchase_product(fk_invoice_number, fk_product_cod, checkout_amount) VALUES (1002, 14, 10);
INSERT INTO bookstore.purchase_product(fk_invoice_number, fk_product_cod, checkout_amount) VALUES (1002, 22, 20);


INSERT INTO bookstore.purchase (invoice_number, fk_customer_cpf) VALUES (1003, '12345678901');
INSERT INTO bookstore.purchase_product(fk_invoice_number, fk_product_cod, checkout_amount) VALUES (1003, 23, 2);
INSERT INTO bookstore.purchase_product(fk_invoice_number, fk_product_cod, checkout_amount) VALUES (1003, 22, 4);