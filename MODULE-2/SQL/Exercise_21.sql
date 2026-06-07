SELECT
    s.student_id,
    s.full_name,
    COUNT(a.assignment_id) AS total_assignments
FROM Students s
JOIN Assignments a
    ON s.student_id = a.student_id
GROUP BY s.student_id, s.full_name
ORDER BY total_assignments DESC
LIMIT 5
