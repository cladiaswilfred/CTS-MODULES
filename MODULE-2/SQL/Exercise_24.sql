SELECT
    c.car_id,
    c.model,
    ROUND(
        AVG(
            TIMESTAMPDIFF(
                DAY,
                r.pickup_date,
                r.return_date
            )
        ),
        0
    ) AS avg_rental_days
FROM Cars c
JOIN Rentals r
    ON c.car_id = r.car_id
GROUP BY c.car_id, c.model
ORDER BY avg_rental_days DESC
