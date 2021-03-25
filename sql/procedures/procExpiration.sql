CREATE OR REPLACE PROCEDURE proc_expi(
    dateUpload date
)
dateToday date := sysDate;
BEGIN
    DELETE FROM LesFichiersImage WHERE (dateToday-dateUpload) >= 10;
END;
/