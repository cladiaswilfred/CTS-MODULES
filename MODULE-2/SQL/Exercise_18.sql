SELECT
    p.product_id,
    p.name
FROM Products p
LEFT JOIN Reviews r
    ON p.product_id = r.product_id
WHERE r.review_id IS NULL
