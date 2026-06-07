SELECT
    DATE_FORMAT(sale_date, '%Y-%m') AS month,
    COUNT(sale_id) AS total_sales
FROM Sales
WHERE sale_date >= CURDATE() - INTERVAL 12 MONTH
GROUP BY DATE_FORMAT(sale_date, '%Y-%m')
ORDER BY month
