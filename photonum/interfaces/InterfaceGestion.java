package photonum.interfaces;

import java.util.List;

import photonum.dao.FichierImageDAO;
import photonum.objects.Commande;
import photonum.objects.StatutCommande;
import photonum.utils.*;

/**
 * cette classe permet l'interaction de l'utilisateur avec la partie gestion de l'application
 */
public class InterfaceGestion {

    /**
     * permet de passer toutes les commande du status <b>PRETE_ENVOI</b> à <b>ENVOYEE</b>
     * @param cmds la List<{@link Commande}> de toutes les commande à passer du status <b>PRETE_ENVOI</b> à <b>ENVOYEE</b>
     */
    private static void envoyerToutesCommandes(List<Commande> cmds) {
        if (cmds.size() > 0) {
            int i = 0;
            for (Commande c : cmds) {
                if (c.getStatus() == StatutCommande.PRETE_ENVOI) {
                    c.setStatus(StatutCommande.ENVOYEE);
                    if (c.mettreAJour()) {
                        genererFacture(c);
                        i++;
                    }
                }
            }
            System.out.println(i+"/"+cmds.size()+" commandes envoyées avec succès !");
        } else {
            System.out.println("Il n'y a aucune commande à envoyer.");
        }
    }

    /**
     * permet de passer commande {@value id} du status <b>PRETE_ENVOI</b> à <b>ENVOYEE</b>
     * @param id de la commande à passer du status <b>PRETE_ENVOI</b> à <b>ENVOYEE</b>
     */
    private static void envoyerUneCommande(int id) {
        Commande cmd = Commande.get(id);
        if (cmd.getStatus() == StatutCommande.PRETE_ENVOI) {
            cmd.setStatus(StatutCommande.ENVOYEE);
            if (cmd.mettreAJour()) {
                genererFacture(cmd);
                System.out.println("La commande n°"+id+" a bien été envoyée !");
            } else {
                System.out.println("La commande n°"+id+" n'a pas pu être envoyée.");
            }
        } else {
            System.out.println("La commande n°"+id+" n'est pas encore prête ou a déjà été envoyée.");
        }
    }

    /**
     * permet d'afficher toutes les {@link Commande} avec le status <b>PRETE_ENVOI</b>
     */
    private static void afficherCommandesPretes() {
        List<Commande> cmds = Commande.getByStatus(StatutCommande.PRETE_ENVOI);
        if (cmds.size() > 0) {
            System.out.println("ID | Compte | Date | Livraison | Statut | Code promo");
            cmds.forEach(c -> System.out.println(c.toString()));
        } else {
            System.out.println("Aucune commande à envoyer.");
        }
    }

    /**
     * permet de demarrer le menu de l'interface de gestion
     * sous la forme :
     * <h3>Exemple</h3>
     * <table>
	 		<tr><td>--- Gestion de l'application ---</td></tr>
	 		<tr><td>1. Afficher les commandes prêtes à l'envoi</td></tr>
			<tr><td>2. Envoyer toutes les commandes prêtes à l'envoi</td></tr>
            <tr><td>3. Envoyer une commande</td></tr>
            <tr><td>4. Supprimer les images inutilisées</td></tr>
            <tr><td>5. Retour au menu principal</td></tr>
	 * </table>
     */
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
                    envoyerToutesCommandes(Commande.getByStatus(StatutCommande.PRETE_ENVOI));
                    break;
                case 3:
                    System.out.println("Entrez l'identifiant d'une commande à envoyer :");
                    int id = LectureClavier.lireEntier(">");
                    envoyerUneCommande(id);
                    break;
                case 4:
                    if (FichierImageDAO.cleanExpiredImages())
                        System.out.println("Les images inutilisées depuis plus de 10 jours ont été supprimées !");
                    else System.out.println("Les images inutilisées n'ont pas pu être supprimées...");
                case 5:
                default:
                    break;
            }
        }
    }

    /**
     * Permet de générer la facture d'une commande.
     * @param c Commande de laquelle on effectue la facture.
     */
    private static void genererFacture(Commande c) {
        System.out.println(c.facture());
    }
}
