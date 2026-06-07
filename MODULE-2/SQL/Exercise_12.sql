SELECT
    d.dept_id,
    d.dept_name,
    COUNT(e.emp_id) AS total_employees
FROM Departments d
JOIN Employees e
    ON d.dept_id = e.dept_id
GROUP BY d.dept_id, d.dept_name
HAVING COUNT(e.emp_id) = (
    SELECT MAX(emp_count)
    FROM (
        SELECT COUNT(emp_id) AS emp_count
        FROM Employees
        GROUP BY dept_id
    ) AS counts
)
