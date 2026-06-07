SELECT
    s.supplier_id,
    s.supplier_name
FROM Suppliers s
LEFT JOIN Products p
    ON s.supplier_id = p.supplier_id
WHERE p.product_id IS NULL
