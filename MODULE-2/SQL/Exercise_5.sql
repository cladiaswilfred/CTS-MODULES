SELECT
    w.region,
    COUNT(DISTINCT i.item_id) AS total_items
FROM Warehouses w
JOIN Inventory i
    ON w.warehouse_id = i.warehouse_id
GROUP BY w.region
ORDER BY total_items DESC
LIMIT 5
