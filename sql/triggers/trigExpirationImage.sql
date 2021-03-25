CREATE OR REPLACE TRIGGER expi_img_trg
    AFTER
    INSERT OR UPDATE
    ON LesFichiersImage
BEGIN
    expi_img_proc();
END;
/