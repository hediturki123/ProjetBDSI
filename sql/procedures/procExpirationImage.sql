CREATE OR REPLACE PROCEDURE expi_img_proc
IS
BEGIN
    DELETE FROM LesFichiersImage WHERE (sysDate-dateUpload) >= 10 AND chemin NOT IN (SELECT chemin FROM LesPhotos);
END;
/