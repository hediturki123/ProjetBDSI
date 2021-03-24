package photonum.interfaces;
public class InterfaceGestion {
    

    public void chercherCommande() {
        System.out.println("Entrer le mail du client : ");
        String mail = LectureClavier.lireChaine();
        Commande commande = new Commande(squellete_appli.conn);
        commande.read(mail);
        
    } 


    public void changerEtatCommande(Commande commande) {
        if (commande.status == "preteEnvoi") {
            commande.status = "envoyee";
        }
        commande.update(commande.idCommande, commande.mail, commande.dateCommande, commande.estLivreChezClient, commande.status, commande.code);
        commande.genererFacture(commande.id);
    }

    public void genererCodePromo(Commande commande) {

    }

    public void menu() {
        System.out.println("Etats des commandes prêtes à l'envoi : ");
        DAO<Commande> commandeDAO = new CommandeDAO(squellete_appli.conn);
        Commande [] commandes;
        
        
        for (...) {
            Commande c = commandeDAO.read(c.status);
            commandes. 
        }
        
        /*String mailClient = LectureClavier.lireChaine();

        int choix = LectureClavier.lireEntier("1. Changer l'état d'une commande \n 2. Générer un code promo \n");
        while (choix != -1) {
            switch(choix) {
                case 1 : changerEtatCommande(commande);
                case 2 : genererCodePromo(commande);
                case -1 : break;
                default : break;
            }
        }*/

    }



}
