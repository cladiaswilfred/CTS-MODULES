SELECT
    p.product_id,
    p.name,
    COUNT(o.order_id) AS times_ordered
FROM Products p
JOIN Orders o
    ON p.product_id = o.product_id
GROUP BY p.product_id, p.name
ORDER BY times_ordered DESC
LIMIT 3
