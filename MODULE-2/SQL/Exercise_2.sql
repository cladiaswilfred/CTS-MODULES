SELECT
    s.student_id,
    s.full_name,
    AVG(g.grade) AS average_grade,
    COUNT(g.grade_id) AS grade_count
FROM Students s
JOIN Grades g
    ON s.student_id = g.student_id
GROUP BY s.student_id, s.full_name
HAVING COUNT(g.grade_id) >= 3
ORDER BY average_grade DESC
