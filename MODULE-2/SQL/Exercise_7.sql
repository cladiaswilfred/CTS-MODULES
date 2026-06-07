SELECT
    c.customer_name,
    p.name AS product_name,
    f.stars,
    f.comment
FROM Feedbacks f
JOIN Customers c
    ON f.customer_id = c.customer_id
JOIN Products p
    ON f.product_id = p.product_id
WHERE f.stars < 3
