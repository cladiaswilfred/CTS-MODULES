SELECT
    order_date,
    COUNT(order_id) AS daily_orders
FROM Orders
WHERE order_date >= CURDATE() - INTERVAL 7 DAY
GROUP BY order_date
ORDER BY order_date
