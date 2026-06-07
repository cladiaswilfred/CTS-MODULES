SELECT
    e.emp_id,
    e.emp_name,
    e.email,
    e.hire_date
FROM Employees e
LEFT JOIN TrainingRecords t
    ON e.emp_id = t.emp_id
WHERE e.hire_date >= CURDATE() - INTERVAL 30 DAY
AND t.record_id IS NULL
