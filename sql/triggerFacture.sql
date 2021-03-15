CREATE PROCEDURE facture_proc (
    idCommande NUMBER(3) 
)
AS LANGUAGE JAVA
NAME 'Commande.genererFacture(int)';

CREATE trigger facture_trg
    AFTER 
    UPDATE OF status
    ON LesCommandes
    FOR EACH ROW
    WHEN (:NEW.status = 'envoyee')
    CALL facture_proc (:NEW.idCommande);