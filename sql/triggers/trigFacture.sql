CREATE OR REPLACE TRIGGER facture_trg
    AFTER
    UPDATE OF status
    ON LesCommandes
    FOR EACH ROW
    WHEN (:NEW.status = 'envoyee')
    CALL facture_proc (:NEW.idCommande);
/