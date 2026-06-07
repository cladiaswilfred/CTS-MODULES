SELECT
    student_id,
    course_id,
    COUNT(*) AS enrollment_count
FROM Enrollments
GROUP BY student_id, course_id
HAVING COUNT(*) > 1
