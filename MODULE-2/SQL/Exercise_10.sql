SELECT
    s.student_id,
    s.full_name
FROM Students s
JOIN Enrollments e
    ON s.student_id = e.student_id
LEFT JOIN Assignments a
    ON s.student_id = a.student_id
WHERE a.assignment_id IS NULL
GROUP BY s.student_id, s.full_name
