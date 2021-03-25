CREATE OR REPLACE TRIGGER promo_trg
    AFTER
    CREATE
    ON LesCommandes
    FOR EACH ROW
    WHEN ((SELECT prixTotal FROM LesCommandesPrix WHERE idCommande = :NEW.idCommande) >= 100)
    CALL promo_proc (:NEW.idCommande);
/