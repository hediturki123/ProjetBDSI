CREATE OR REPLACE TRIGGER util_promo_trg
    AFTER
    INSERT OR UPDATE
    ON LesCommandes
BEGIN
    IF :NEW:code IS NOT NULL THEN
        UPDATE LesCodesPromo SET estUtilise = 1 WHERE code = :NEW.code;
    END IF;
END;
/