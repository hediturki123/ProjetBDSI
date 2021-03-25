CREATE OR REPLACE PROCEDURE facture_proc (
    idCommande NUMBER(3)
)
AS LANGUAGE JAVA
NAME 'Commande.genererFacture(int)';
/