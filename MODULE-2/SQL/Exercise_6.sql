SELECT
    p.product_id,
    p.name,
    COUNT(f.feedback_id) AS total_feedback
FROM Products p
LEFT JOIN Feedbacks f
    ON p.product_id = f.product_id
GROUP BY p.product_id, p.name
ORDER BY total_feedback DESC
