SELECT b.*
FROM bookstore.book b
JOIN bookstore.publisher p ON b.fk_publisher_fname = p.fname
WHERE p.fname = 'Editora Abril';