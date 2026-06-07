SELECT
    c.customer_id,
    c.customer_name,
    COUNT(DISTINCT o.product_id) AS products_bought,
    COUNT(DISTINCT r.review_id) AS reviews_given
FROM Customers c
LEFT JOIN Orders o
    ON c.customer_id = o.customer_id
LEFT JOIN Reviews r
    ON c.customer_id = r.customer_id
GROUP BY c.customer_id, c.customer_name
ORDER BY c.customer_id
