SELECT m.*
FROM bookstore.magazine m
JOIN bookstore.theme t ON m.fk_theme_fname = t.fname
WHERE t.fname = 'Empresarial';