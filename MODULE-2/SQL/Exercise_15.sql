SELECT
    a1.doctor_id,
    a1.patient_id,
    a1.start_time AS appt1_start,
    a1.end_time AS appt1_end,
    a2.start_time AS appt2_start,
    a2.end_time AS appt2_end
FROM Appointments a1
JOIN Appointments a2
    ON a1.doctor_id = a2.doctor_id
    AND a1.appt_id < a2.appt_id
WHERE a1.start_time < a2.end_time
  AND a1.end_time > a2.start_time
