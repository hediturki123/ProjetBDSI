package photonum.interfaces;

import java.util.List;
import photonum.PhotoNum;
import photonum.dao.CommandeDAO;
import photonum.dao.FichierImageDAO;
import photonum.objects.Commande;
import photonum.objects.StatutCommande;
import photonum.utils.*;

public class InterfaceGestion {

    private final static CommandeDAO commandeDAO = new CommandeDAO(PhotoNum.conn);
    private final static FichierImageDAO fichierImageDAO = new FichierImageDAO(PhotoNum.conn);

    private static void envoyerToutesCommandes(List<Commande> cmds) {
        if (cmds.size() > 0) {
            int i = 0;
            for (Commande c : cmds) {
                if (c.getStatus() == StatutCommande.PRETE_ENVOI) {
                    c.setStatus(StatutCommande.ENVOYEE);
                    i += commandeDAO.update(c) ? 1 : 0;
                }
            }
            System.out.println(i+"/"+cmds.size()+" commandes envoyées avec succès !");
        } else {
            System.out.println("Il n'y a aucune commande à envoyer.");
        }
    }

    private static void envoyerUneCommande(int id) {
        Commande commande = commandeDAO.read(id);
        if (commande.getStatus() == StatutCommande.PRETE_ENVOI) {
            commande.setStatus(StatutCommande.ENVOYEE);
            if (commandeDAO.update(commande)) {
                System.out.println("La commande n°"+id+" a bien été envoyée !");
            } else {
                System.out.println("La commande n°"+id+" n'a pas pu être envoyée.");
            }
        } else {
            System.out.println("La commande n°"+id+" n'est pas encore prête ou a déjà été envoyée.");
        }
    }

    private static void afficherCommandesPretes() {
        List<Commande> cmds = commandeDAO.readAllByStatus(StatutCommande.PRETE_ENVOI);
        if (cmds.size() > 0) {
            System.out.println("ID | Compte | Date | Livraison | Statut | Code promo");
            cmds.forEach(c -> System.out.println(c.toString()));
        } else {
            System.out.println("Aucune commande à envoyer.");
        }
    }

    public static void menuPrincipal() {
        int choix = -1;
        while(choix != 5) {
            System.out.print(
                "\n--- Gestion de l'application ---\n" +
                "\t1. Afficher les commandes prêtes à l'envoi\n" +
                "\t2. Envoyer toutes les commandes prêtes à l'envoi\n" +
                "\t3. Envoyer une commande\n" +
                "\t4. Supprimer les images inutilisées\n" +
                "\t5. Retour au menu principal\n"
            );
            choix = LectureClavier.lireEntier(">");
            switch(choix) {
                case 1:
                    afficherCommandesPretes();
                    break;
                case 2:
                    envoyerToutesCommandes(commandeDAO.readAllByStatus(StatutCommande.PRETE_ENVOI));
                    break;
                case 3:
                    System.out.println("Entrez l'identifiant d'une commande à envoyer :");
                    int id = LectureClavier.lireEntier(">");
                    envoyerUneCommande(id);
                    break;
                case 4:
                    if (fichierImageDAO.cleanExpiredImages())
                        System.out.println("Les images inutilisées depuis plus de 10 jours ont été supprimées !");
                    else System.out.println("Les images inutilisées n'ont pas pu être supprimées...");
                case 5:
                default:
                    break;
            }
        }

    }



}
