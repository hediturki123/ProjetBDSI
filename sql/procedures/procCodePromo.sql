CREATE OR REPLACE PROCEDURE code_promo_proc (
    in_code NUMBER,
    in_mail_client VARCHAR2
)
IS
BEGIN
    INSERT INTO LesCodesPromo VALUES (in_code, in_mail_client, 0);
END;
/