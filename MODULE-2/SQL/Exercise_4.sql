SELECT
    d.dept_id,
    d.dept_name,
    COUNT(e.emp_id) AS emp_count
FROM Departments d
JOIN Employees e
    ON d.dept_id = e.dept_id
WHERE YEAR(e.hire_date) BETWEEN 2019 AND 2024
GROUP BY d.dept_id, d.dept_name
