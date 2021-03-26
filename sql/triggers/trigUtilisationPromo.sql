CREATE OR REPLACE TRIGGER util_promo_trg
    AFTER
    INSERT OR UPDATE
    ON LesCommandes
    FOR EACH ROW 
BEGIN
    IF :new.codePromo IS NOT NULL THEN
        UPDATE LesCodesPromo SET estUtilise = 1 WHERE code = :new.codePromo;
    END IF;
END;
/