SELECT
    h.hotel_id,
    h.name,
    COUNT(DISTINCT b.booking_id) AS total_bookings,
    ROUND(AVG(r.rating), 2) AS avg_rating
FROM Hotels h
LEFT JOIN Bookings b
    ON h.hotel_id = b.hotel_id
LEFT JOIN Reviews r
    ON h.hotel_id = r.hotel_id
WHERE h.is_active = 'yes'
GROUP BY h.hotel_id, h.name
