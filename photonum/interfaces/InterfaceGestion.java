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
        System.out.println("idCommande | mail | dateCommande | estLivreChezClient | Statut | codePromo");
        co.forEach(
            c -> {
                System.out.println(c.getIdCommande() + c.getMail() + c.getDateCommande()
                + c.getEstLivreChezClient() + c.getStatus() + c.getCodePromo());
            }
        );
        Commande commande = commandeDAO.read(id);
        commande.setStatus(StatutCommande.ENVOYEE);
        commandeDAO.update(commande);
    }


    public static void menuPrincipal() {
        List<Commande> commandes = commandeDAO.readAllByStatus(StatutCommande.PRETE_ENVOI);
        int choix = -1;

        while(choix != 3) {
            System.out.print(
                "--- Gérer les commandes ---\n" +
                "1. Envoyer toutes les commandes prêtes à l'envoi\n" +
                "2. Changer le statut d'une commande\n" +
                "3. Retour au menu principal" +
                "> "
            );
            choix = LectureClavier.lireEntier("");
            switch(choix) {
                case 1:
                    envoyerCommandes(commandes);
                    break;
                case 2:
                    System.out.println("Entrer l'identifiant d'une commande à envoyer");
                    int id = LectureClavier.lireEntier("");
                    changerStatutCommande(commandes, id);
                    break;
                case 3:
                default:
                    break;
            }
        }

    }



}
