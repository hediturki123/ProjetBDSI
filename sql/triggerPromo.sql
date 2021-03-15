CREATE PROCEDURE promo_proc (
    idCommande NUMBER(3) 
)
AS LANGUAGE JAVA
NAME 'Promotion.ajouterPromo(int)';

CREATE trigger promo_trg
    AFTER 
    CREATE 
    ON LesCommandes
    FOR EACH ROW
    WHEN ((SELECT prixTotal FROM LesCommandesPrix WHERE idCommande = :NEW.idCommande) >= 100)
    CALL promo_proc (:NEW.idCommande);