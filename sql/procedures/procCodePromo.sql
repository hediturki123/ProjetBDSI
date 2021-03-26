CREATE OR REPLACE PROCEDURE code_promo_proc (
    in_code NUMBER(10),
    in_mail_client VARCHAR2(80)
)
IS
BEGIN
    INSERT INTO LesCodesPromo VALUES (in_code, in_mail_client);
END;
/