package photonum.interfaces;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import photonum.PhotoNum;
import photonum.dao.AdresseDAO;
import photonum.dao.ArticleDAO;
import photonum.dao.CodePromoDAO;
import photonum.dao.CommandeDAO;
import photonum.dao.ImpressionDAO;
import photonum.dao.ProduitDAO;
import photonum.objects.*;
import photonum.utils.LectureClavier;

public class InterfaceCommande {
    private static CommandeDAO cmdDao=new CommandeDAO(PhotoNum.conn);
    private static ImpressionDAO impDao = new ImpressionDAO(PhotoNum.conn);
    private static ArticleDAO articleDAO = new ArticleDAO(PhotoNum.conn);
    //TODO creation commande
    /**
     * Permet de demarrer la creation d'une commande
     * @param c le {@link Client} courant
     */
    public static void creationCommande(Client c) {
        Commande cmd = new Commande();
        cmd.setIdCommande(cmdDao.getLastId() + 1);
        cmd.setMail(c.getMail());
        choixImpression(c, cmd);
    }
    /**
     * permet de choissir les {@link Article} d'une commande
     * @param c le {@link Client} courant
     * @param cmd la {@link Commande} courante
     */
    public static void choixImpression(Client c,Commande cmd){
        ImpressionDAO impDao=new ImpressionDAO(PhotoNum.conn);
        List<Impression> impressionClient=impDao.readAllByClient(c);
        List<Article> articleChoisi= new ArrayList<>();
        if(impressionClient.size() != 0) {
            int choix=-1;
            while(choix!=0){
                while(!(choix>0 && choix<=impressionClient.size())){
                    System.out.println("choissiez l'impression");
                    for (int i = 1; i <= impressionClient.size(); i++) {
                        System.out.println(i + ". " + impressionClient.get(i - 1).getTitre());
                    }
                    choix = LectureClavier.lireEntier(
                            "choisissez une impression pour la voir plus en detail dans la liste ci-dessus \n"
                                    + "ou taper sur 0");
                }
                articleChoisi.add(new Article(cmd.getIdCommande(), impressionClient.get(choix - 1).getIdImpression(),
                        LectureClavier.lireEntier("Quantite : ")));
            }
            InterfaceCodePromo.utilisationCodePromo(c, cmd, articleChoisi);
        } else {
            System.out.println("Vous n'avez pas d'impressions.");
        }

    }
    /**
     * 
     * @param c le {@link Client} courant
     * @param cmd la {@link Commande} courante
     * @param articles la List&lt;{@link Article}&gt; des {@link Article} de la commande cmd
     */
    public static void livraison(Client c,Commande cmd,List<Article> articles){
        Adresse addr =new Adresse();    
        if(LectureClavier.lireOuiNon("Voulez vous être livrée chez vous")){
                cmd.setEstLivreChezClient(true);
                cmd.setAdresseLivraison(c.getNumeroRue(),c.getNomRue(),c.getVille(),c.getCp(),c.getPays());
        }else{
                InterfaceAdresse.choixAdresseLivraison(c,addr);
                cmd.setEstLivreChezClient(false);
                cmd.setAdresseLivraison(addr.getNumeroRue(),addr.getNomRue(),addr.getVille(),addr.getCp(),addr.getPays());
        }
        ValidationCommande(c, cmd, articles);
    }

    /**
     * Etape de validation de la commande  
     * @param c le {@link Client} courant
     * @param cmd la {@link Commande} courante
     * @param articles la List&lt;{@link Article}&gt; des {@link Article} de la commande cmd
     */
    public static void ValidationCommande(Client c,Commande cmd,List<Article> articles){
        ArticleDAO articleDAO=new ArticleDAO(PhotoNum.conn);
        System.out.println("voici le descriptif de votre commande :");
        System.out.println("    " + cmd.toString());
        System.out.println("    Details de vos articles :");
        for (Article a : articles) {
            System.out.println("        " + a.toString());
        }

        if (LectureClavier.lireOuiNon("valider la commande ? (o/n)")) {
            cmd.setDateCommande(Date.valueOf(LocalDate.now()));
            if (cmdDao.create(cmd)) {
                for (Article a : articles)
                    articleDAO.create(a);
                System.out.println("Votre commande n°" + cmd.getIdCommande()
                        + " a bien été enregistrer , Merci de votre confiance !");
            } else {
                System.out
                        .println("desolée mais votre commande n'a pas pus etre enregistrer , veuillez recommencer  !");
            }
        } else {
            int choix = -1;
            while (!(choix > 0 && choix <= 4)) {
                choix = LectureClavier.lireEntier("Voulez vous revenir a une etape ou quitter ? "
                        + "\n(attention reneir a une etape vous oblige a faire les suivantes)"
                        + "\n1. Choisir les Impressions" + "\n2. Choisir le CodePromo"
                        + "\n3. Changer l'adresse de livraison" + "\n4. Abandonner\n");
            }
            switch(choix){
                case 1: choixImpression(c, cmd);
                        break;
                case 2:InterfaceCodePromo.utilisationCodePromo(c, cmd, articles);
                        break;
                case 3:livraison(c, cmd, articles);
                        break;
                case 4:break;
            }
        }
    }

    public static void affichageCommande(Client c) {
        List<Commande> commandesClient = cmdDao.readAllByClient(c);
        if (commandesClient.size() != 0) {
            System.out.println("Voici toutes vos commandes :");
            for (int i = 1; i <= commandesClient.size(); i++) {
                System.out.println(i + ". " + commandesClient.get(i - 1).toString());
            }
            int choix = LectureClavier.lireEntier("si vous voulez plus de detail sur une commande\n"
                    + "choissiez une commande dans la liste ci-dessus\n" + "sinon taper 0");
            while (choix != 0) {
                while (!(choix > 0 && choix <= commandesClient.size())) {
                    System.out.println("\nvous n'avez pas mis un numero de commande valide\n");
                    System.out.println("Voici toutes vos commandes :");
                    for (int i = 1; i <= commandesClient.size(); i++) {
                        System.out.println(i + ". " + commandesClient.get(i - 1).toString());
                    }
                    choix = LectureClavier.lireEntier("si vous voulez plus de detail sur une commande\n"
                            + "choissiez une commande dans la liste ci-dessus\n" + "sinon taper 0");
                }
                afficherDetailCommande(commandesClient.get(choix - 1));

                System.out.println("Voici toutes vos commandes :");
                for (int i = 1; i <= commandesClient.size(); i++) {
                    System.out.println(i + ". " + commandesClient.get(i - 1).toString());
                }
                choix = LectureClavier.lireEntier("si vous voulez plus de detail sur une commande\n"
                        + "choissiez une commande dans la liste ci-dessus\n" + "sinon taper 0");
            }
        } else {
            System.out.println("Désolé mais vous n'avez pas fait encore de commande \n");
        }
    }

    public static void afficherDetailCommande(Commande c) {
        List<Article> tabArticles = articleDAO.readAllByCommande(c);
        System.out.println("\n\nLes details de votre commande :");
        if (tabArticles.size() > 0) {
            for (int i = 1; i <= tabArticles.size(); i++) {
                System.out.println("    " + tabArticles.get(i - 1).toString());
            }
        } else {
            System.out.println("    aucun article dans votre commande !\n");
        }
    }
}
