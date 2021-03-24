package photonum.interfaces;

import java.util.List;
import photonum.PhotoNum;
import photonum.dao.CommandeDAO;
import photonum.objects.Commande;
import photonum.objects.StatutCommande;
import photonum.utils.*;

public class InterfaceGestion {

    private static CommandeDAO commandeDAO = new CommandeDAO(PhotoNum.conn);

    public void genererCodePromo(Commande commande) {

    }

    private static void envoyerCommandes(List<Commande> commandes) {
        commandes.forEach(
            c -> {
                c.setStatus(StatutCommande.ENVOYEE);
                commandeDAO.update(c);
            }
        );
    }

    private static void changerStatutCommande(List<Commande> co, int id) {
        System.out.println("idCommande | mail | date | estLivreChezClient | Statut | codePromo");
        co.forEach(
            c -> {
                System.out.println(co.getIdCommande() + co.getMail() + co.getDateCommande() 
                + co.getEstLivreChezClient() + co.getStatus() + co.getCodePromo());
            }
        );  
        Commande commande = commandeDAO.read(id);
        commande.setStatus(StatutCommande.ENVOYEE);
        commandeDAO.update(commande);
    }


    public static void menuPrincipal() {
        System.out.println("-- Gérer les commandes ---\n");
        List<Commande> commandes = commandeDAO.readAllByStatus(StatutCommande.PRETE_ENVOI);
        // ici
        System.out.println(
            "1. Envoyer toutes les commandes prêtes à l'envoi\n" +
            "2. Changer le statut d'une commande\n" + "> "
        );
        int choix = LectureClavier.lireEntier("");

        while(choix != -1) {
            switch(choix) {
                case 1 :
                    envoyerCommandes(commandes);
                    break;

                case 2 :
                    System.out.println("Entrer l'identifiant d'une commande à envoyer");
                    int id = LectureClavier.lireEntier("");
                    changerStatutCommande(commandes, id);

                default : 
                    System.out.println(
                        "1. Envoyer toutes les commandes Prêtes à l'envoi \n" +
                        "2. Changer le statut d'une commande\n" + "> "
                        );
                    choix = LectureClavier.lireEntier("");
            }
        }

    }



}
