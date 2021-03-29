package photonum.interfaces;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import photonum.dao.CommandeDAO;
import photonum.objects.*;
import photonum.utils.LectureClavier;

public class InterfaceCommande {

    /**
     * Permet de demarrer la creation d'une commande
     * @param c le {@link Client} courant
     */
    public static void creationCommande(Client c) {
        Commande cmd = new Commande();
        cmd.setIdCommande(CommandeDAO.getLastId()+1);
        cmd.setMail(c.getMail());
        choixImpression(c, cmd,false);
    }

    /**
     * Permet de choisir les {@link Article}s d'une commande.
     * @param c le {@link Client} courant
     * @param cmd la {@link Commande} courante
     * @param modif <b>true</b> s'il y a une modification en cours ; <b>false</b> sinon.
     */
    public static void choixImpression(Client c, Commande cmd , boolean modif){
        List<Impression> impressionClient = c.getImpressions();
        List<Article> articleChoisi = new ArrayList<>();
        if (impressionClient.size() != 0) {
            int choix = -1;
            int last = impressionClient.size() + 1;
            while (choix != last) {
                String message = "Choisir une impression ou bien terminer :\n";
                for (int i = 1; i <= impressionClient.size(); i++) {
                    message += i + ". " + impressionClient.get(i - 1).getTitre() + "\n";
                }
                message += last + ". Terminer le choix des impressions";
                choix = LectureClavier.lireEntier(message);
                if (choix < 0 || choix > last) {
                    System.out.println("Vous n'avez pas choisi une impression valide.");
                } else if (choix != last) {
                    articleChoisi.add(new Article(
                        cmd.getIdCommande(),
                        impressionClient.get(choix - 1).getIdImpression(),
                        LectureClavier.lireEntier("Quantité : ")
                    ));
                }
            }
            if (!modif) InterfaceCodePromo.utilisationCodePromo(c, cmd, articleChoisi,false);
                else validationCommande(c, cmd, articleChoisi,modif);
        } else {
            System.out.println("Vous n'avez pas d'impression.");
        }
    }

    /**
     * @param c le {@link Client} courant
     * @param cmd la {@link Commande} courante
     * @param articles la List&lt;{@link Article}&gt; des {@link Article} de la commande cmd
     * @param modif un boolean<pre>True si on est en train de modifier une commande </pre> <pre>False sinon </pre>
     */
    public static void livraison(Client c, Commande cmd, List<Article> articles, boolean modif) {
        Adresse addr = new Adresse();
        boolean livreChezClient = LectureClavier.lireOuiNon("Voulez-vous être livré à votre domicile ? (o/n)");
        cmd.setEstLivreChezClient(livreChezClient);
        if (livreChezClient) {
            cmd.setAdresseLivraison(c.getNumeroRue(), c.getNomRue(), c.getVille(), c.getCp(), c.getPays());
            c.checkAdresseExist(new Adresse(c.getMail(),c.getNumeroRue(), c.getNomRue(), c.getVille(), c.getCp(), c.getPays()));
        } else {
            InterfaceAdresse.choixAdresseLivraison(c, addr);
            cmd.setAdresseLivraison(addr);
        }
        validationCommande(c, cmd, articles,modif);
    }

    /**
     * Etape de validation de la commande
     * @param c le {@link Client} courant
     * @param cmd la {@link Commande} courante
     * @param articles la List&lt;{@link Article}&gt; des {@link Article} de la commande cmd
     */
    public static void validationCommande(Client c, Commande cmd, List<Article> articles,boolean modif){
        if(!modif)cmd.setDateCommande(Date.valueOf(LocalDate.now()));
        if(!modif)cmd.setStatus(StatutCommande.EN_COURS);
        System.out.println("Voici le descriptif de votre commande.");
        System.out.println(cmd.toString());
        System.out.println("Details de vos articles :");
        for (Article a : articles) {
            System.out.println("\t" + a.factureString());
        }
        if (LectureClavier.lireOuiNon("Valider la commande ? (o/n)")) {
            if (!modif) {
                if(cmd.nouvelleCommande()){
                    cmd.ajouterArticles(articles);
                     System.out.println(
                        "Votre commande (n°"+cmd.getIdCommande()+") a bien été enregistrée. Il ne vous reste plus qu'à payer !"
                    );
                    paiement(cmd);
                } else {
                    System.out.println("Desolé, mais votre commande n'a pas pu etre enregistrée ! Veuillez essayer à nouveau.");
                }
            } else {
                if(cmd.mettreAJour()) System.out.println("Votre commande (n°"+cmd.getIdCommande()+") a bien été modifiée.");
                else System.out.println("Votre commande (n°"+cmd.getIdCommande()+") n'a pas pu être modifiée.");
            }
        } else {
            int choix = -1;
            while (!(choix > 0 && choix <= 4)) {
                choix = LectureClavier.lireEntier(
                    "Voulez vous revenir à une étape ou quitter ? (Attention, revenir à une étape vous oblige à faire les suivantes.)\n"+
                    "\t1. Choisir les impressions\n" +
                    "\t2. Appliquer un code promo\n" +
                    "\t3. Changer l'adresse de livraison\n" +
                    "\t4. Abandonner\n"+
                    "> "
                );
            }
            switch (choix){
                case 1: choixImpression(c, cmd,true); break;
                case 2: InterfaceCodePromo.utilisationCodePromo(c, cmd, articles,true); break;
                case 3: livraison(c, cmd, articles,true); break;
                case 4:break;
                default: System.err.println("Veuillez indiquer un nombre entre 1 et 4."); break;
            }
        }
    }

    /**
     * cette fonction simule l'étape de paiement à un utilisateur
     * et va mettre à <b>PRETE_ENVOI</b> le statut de la commande.
     * Le client obtient également un code
     * @param cmd La {@link Commande} qu'on veut payer ou non
     */
    public static void paiement(Commande cmd){
        if (LectureClavier.lireOuiNon("Voulez-vous payer maintenant ? (o/n)")) {
            System.out.println("Merci pour votre achat ! Votre commande est maintenant prête à être envoyée.");
            cmd.setStatus(StatutCommande.PRETE_ENVOI);
            cmd.mettreAJour();
            // Si la commande dépasse 100€, on donne au client un nouveau code promo !
            if (cmd.getPrixTotal() >= 100)
                if (CommandeDAO.ajouterPromo(cmd.getClient())) {
                    System.out.println("Vous obtenez un nouveau code promo. (Raison : achats > 100€)");
                } else {
                    System.err.println("Nous n'avons pas pu vous donner un code promo...");
                }
        } else {
            System.out.println("Annulation. Vous pouvez modifier votre commande tant qu'elle n'est pas payée.");
        }
    }
    /**
     * Permet d'afficher les commande d'un {@link Client}
     * @param c le {@link Client} a qui on veut afficher ses commandes
     */
    public static void affichageCommande(Client c) {
        List<Commande> commandesClient = c.getCommandes();
        if (commandesClient.size() != 0) {
            System.out.println("Voici toutes vos commandes :");
            for (int i = 1; i <= commandesClient.size(); i++) {
                System.out.println(i + ". " + commandesClient.get(i - 1).toString());
            }
            int choix = LectureClavier.lireEntier("Si vous voulez plus de details sur une commande, choisissez-en une dans la liste ci-dessus ; sinon, taper 0.");

                    //reafire la boucle ici
            while (choix != 0) {
                while (!(choix > 0 && choix <= commandesClient.size())) {
                    System.out.println("\nVous n'avez pas mis un numéro de commande valide.");
                    System.out.println("Voici toutes vos commandes :");
                    for (int i = 1; i <= commandesClient.size(); i++) {
                        System.out.println("\t"+ i + ". " + commandesClient.get(i - 1).toString());
                    }
                    choix = LectureClavier.lireEntier("Si vous voulez plus de details sur une commande, choisissez-en une dans la liste ci-dessus ; sinon, taper 0.");
                }
                afficherDetailCommande(commandesClient.get(choix - 1));

                System.out.println("Voici toutes vos commandes :");
                for (int i = 1; i <= commandesClient.size(); i++) {
                    System.out.println(i + ". " + commandesClient.get(i - 1).toString());
                }
                choix = LectureClavier.lireEntier("Si vous voulez plus de details sur une commande, choisissez une commande dans la liste ci-dessus (sinon taper 0).");
            }
        } else {
            System.out.println("Désolé, mais vous n'avez pas encore fait de commande.");
        }
    }
    /**
     * Permet d'afficher les détails d'une commande
     * @param c la {@link Commande} pour laquel on veut afficher ses detail
     */
    public static void afficherDetailCommande(Commande c) {
        List<Article> tabArticles = c.getArticles();
        System.out.println(c.toString());
        System.out.println("\n\nLes details de votre commande :");
        if (tabArticles.size() > 0) {
            for (int i = 1; i <= tabArticles.size(); i++) {
                System.out.println("\t" + tabArticles.get(i - 1).factureString());
            }
        } else {
            System.out.println("Aucun article dans votre commande !\n");
        }
    }
    /**
     * Gere l'interaction avec l'utilisateur pour lui faire choisir une commande a modifier
     * @param c un {@link Client}
     */
    public static void MenuModifCommande(Client c){
        List<Commande> commandesClient = c.getCommandes();
        int choix=-1;
        if (commandesClient.size() != 0) {
            int last = commandesClient.size() + 1;
            while (choix != last) {
                String message = "Choisir une commande ou bien terminer :\n";
                for (int i = 1; i <= commandesClient.size(); i++) {
                    message += i + ". " + commandesClient.get(i - 1).toString() + "\n";
                }
                message += last + ". Terminer de modifier les commandes";
                choix = LectureClavier.lireEntier(message);
                if (choix < 0 || choix > last) {
                    System.out.println("Vous n'avez pas choisi une commande valide.");
                } else if (choix != last) {
                    modificationCommande(c, commandesClient.get(choix - 1));
                }
            }
        } else {
            System.out.println("vous n'avez aucune commande");
        }
    }

    /**
     * ici un petit menu permettant de modifier une commande
     * sous cette forme
     * <h3>Exemple :</h3>
	 * <table>
	 		<tr><td>--- Modification Commande ---</td></tr>
	 		<tr><td>1. Modifier le contenu</td></tr>
			<tr><td>2. Modifier le codePromo</td></tr>
			<tr><td>3. Modifier l'adresse de livraison</td></tr>
			<tr><td>4. Payer</td></tr>
			<tr><td>5. Sortir</td></tr>
	* </table>
     * @param c un {@link Client}
     * @param cmd la {@link Commande} a modifieé
     */
    public static void  modificationCommande(Client c,Commande cmd){
        if (cmd.getStatus() == StatutCommande.ENVOYEE) {
            System.out.println("Votre commande a déjà été envoyée et ne peut donc pas être modifiée.");
        } else if (cmd.getStatus() == StatutCommande.PRETE_ENVOI) {
            System.out.println("Votre commande est en cours de préparation, et ne peut donc pas être modifiée.");
        } else {
            int choix = -1; 
            List<Article> listePanier = cmd.getArticles();
            while (choix != 5) {
                choix=LectureClavier.lireEntier(
                    "--- Modification Commande ---\n" +
                    "\t1. Modifier le contenu\n" +
                    "\t2. Modifier le codePromo\n" +
                    "\t3. Modifier l'adresse de livraison \n" +
                    "\t4. Payer\n" +
                    "\t5. Sortir\n" +
                    "> "
                );
                switch (choix) {
                    case 1: choixImpression(c, cmd, true);break;
                    case 2: InterfaceCodePromo.utilisationCodePromo(c, cmd,listePanier,true);break;
                    case 3: livraison(c, cmd,listePanier, true); break;
                    case 4: if(cmd.getStatus()!=StatutCommande.PRETE_ENVOI)paiement(cmd);else System.out.println("Votre commande est déja payée !");break;
                    case 5: break;
                    default: System.err.println("Veuillez indiquer un nombre entre 1 et 5."); break;
                }
            }
            System.out.println("Merci d'utiliser nos services !");
        }
    }
}