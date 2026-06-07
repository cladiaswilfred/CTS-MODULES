SELECT
    c.customer_name,
    p.name,
    p.warehouse,
    p.launch_date
FROM Customers c
JOIN Orders o
    ON c.customer_id = o.customer_id
JOIN Products p
    ON o.product_id = p.product_id
WHERE p.in_stock = 'yes'
AND c.region = p.warehouse
ORDER BY p.launch_date
