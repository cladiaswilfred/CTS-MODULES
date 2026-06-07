SELECT
    customer_name,
    COUNT(order_id) AS total_orders
FROM Customers c
JOIN Orders o ON c.customer_id = o.customer_id
GROUP BY customer_name
HAVING COUNT(order_id) > 1
