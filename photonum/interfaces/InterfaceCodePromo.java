package photonum.interfaces;

import java.util.List;

import photonum.objects.Article;
import photonum.objects.Client;
import photonum.objects.CodePromo;
import photonum.objects.Commande;
import photonum.utils.LectureClavier;

/**
 * cette class permet toutes les interaction avec un utilisateur pour tous ce
 * qui concerne les {@link CodePromo}
 */
public class InterfaceCodePromo {

    /**
     * Presente tous les {@link CodePromo} actif du client
     * @param c le {@link Client}
     */
    public static void presentationCodePromo(Client c) {
        List<CodePromo> codeClients = c.getCodesPromo(false);
        if (codeClients.size() != 0) {
            System.out.println("Vos codes promo :");
            for (int i = 1; i <= codeClients.size(); i++) {
                System.out.println("Code n°" + i + " : " + codeClients.get(i - 1).getCode());
            }
        } else {
            System.out.println("Désolé, vous n'avez aucun code promo ....\n");
        }
    }

    /**
     * permet lors d'une commande de demander au client courant si il veut utiliser
     * un {@link CodePromo}
     * @param c        le {@link Client} courant
     * @param cmd      la {@link Commande} courante
     * @param articles List&lt;{@link Article}&gt; les articles de la commande cmd
     * @param modif un boolean<pre>True si on est en train de modifier une commande </pre> <pre>False sinon </pre>
     */
    public static void  utilisationCodePromo(Client c, Commande cmd, List<Article> articles, boolean modif) {
        List<CodePromo> cpDispo = c.getCodesPromo(false);
        CodePromo cpUtilise = new CodePromo();
        if (LectureClavier.lireOuiNon("Voulez-vous utiliser un code promo ? (o/n)")) {
            if (cpDispo.size() > 0) {
                int choix = -1;
                while (!(choix > 0 && choix <= cpDispo.size())) {
                    System.out.println("Vos codes promo :");
                    for (int i = 1; i <= cpDispo.size(); i++) {
                        System.out.println("\t" + i + ". " + cpDispo.get(i - 1));
                    }
                    choix = LectureClavier.lireEntier("Choisissez un code à utiliser dans la liste ci-dessus :");
                }
                cpUtilise = new CodePromo(cpDispo.get(choix - 1).getMailClient(), cpDispo.get(choix - 1).getCode(),
                        cpDispo.get(choix - 1).estUtilise());
            } else {
                System.out.println("Vous n'avez aucun code promo à utiliser.");
            }
        } else {
            System.out.println("Vous n'utilisez pas de code promo.");
        }
        cmd.setCodePromo(cpUtilise.getCode());
        if(modif)InterfaceCommande.validationCommande(c, cmd, articles,true);else InterfaceCommande.livraison(c, cmd, articles,false);
    }
}
