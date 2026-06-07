SELECT
    a.actor_id,
    a.actor_name,
    m.genre,
    COUNT(m.movie_id) AS total_movies
FROM Actors a
JOIN Casts c
    ON a.actor_id = c.actor_id
JOIN Movies m
    ON c.movie_id = m.movie_id
GROUP BY a.actor_id, a.actor_name, m.genre
ORDER BY a.actor_name
