SELECT
    p.category,
    ROUND(AVG(r.score), 2) AS average_score
FROM Products p
JOIN Reviews r
    ON p.product_id = r.product_id
GROUP BY p.category
ORDER BY average_score DESC
