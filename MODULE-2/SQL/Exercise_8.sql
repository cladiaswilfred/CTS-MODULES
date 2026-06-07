SELECT
    p.product_id,
    p.name,
    COUNT(o.order_id) AS order_count
FROM Products p
LEFT JOIN Orders o
    ON p.product_id = o.product_id
WHERE p.in_stock = 'yes'
GROUP BY p.product_id, p.name
