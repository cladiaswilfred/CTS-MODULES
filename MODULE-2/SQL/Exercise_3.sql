SELECT
    p.patient_id,
    p.patient_name,
    p.phone
FROM Patients p
LEFT JOIN Appointments a
    ON p.patient_id = a.patient_id
WHERE a.appt_date < '2025-06-01'
   OR a.appt_date IS NULL
