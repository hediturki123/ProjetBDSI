package photonum.interfaces;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;
import photonum.dao.ArticleDAO;
import photonum.dao.CommandeDAO;
import photonum.dao.ImpressionDAO;
import photonum.objects.*;
import photonum.utils.LectureClavier;

public class InterfaceCommande {

    private static CommandeDAO cmdDao=new CommandeDAO(PhotoNum.conn);
    private static ImpressionDAO impDao = new ImpressionDAO(PhotoNum.conn);
    private static ArticleDAO articleDAO = new ArticleDAO(PhotoNum.conn);

    /**
     * Permet de demarrer la creation d'une commande
     * @param c le {@link Client} courant
     */
    public static void creationCommande(Client c) {
        Commande cmd = new Commande();
        cmd.setIdCommande(cmdDao.getLastId() + 1);
        cmd.setMail(c.getMail());
        choixImpression(c, cmd,false);
    }

    /**
     * permet de choissir les {@link Article} d'une commande
     * @param c le {@link Client} courant
     * @param cmd la {@link Commande} courante
     */
    public static void choixImpression(Client c,Commande cmd ,boolean modif){
        List<Impression> impressionClient=impDao.readAllByClient(c);
        List<Article> articleChoisi= new ArrayList<>();
        if(impressionClient.size() != 0) {
            int choix=-1;
            int last=impressionClient.size()+1;
            while(choix!=last){
                    String message="choissiez l'impression ou bien terminer :\n";
                    for (int i = 1; i <= impressionClient.size(); i++) {
                        message+=i+". " + impressionClient.get(i - 1).getTitre()+"\n";
                    }
                    message+=last+". Terminer le choix des impression";
                    choix= LectureClavier.lireEntier(message);
                    if(choix<0 || choix>last){
                        System.out.println("vous n'avez pas choissi une impression valide");
                    }else if (choix!=last) {
                        articleChoisi.add(new Article(cmd.getIdCommande(), impressionClient.get(choix - 1).getIdImpression(),
                        LectureClavier.lireEntier("Quantite : ")));
                    }
                }
                if(modif)InterfaceCodePromo.utilisationCodePromo(c, cmd, articleChoisi,false);else validationCommande(c, cmd, articleChoisi);
        } else {
            System.out.println("Vous n'avez pas d'impressions.");
        }

    }

    /**
     * @param c le {@link Client} courant
     * @param cmd la {@link Commande} courante
     * @param articles la List&lt;{@link Article}&gt; des {@link Article} de la commande cmd
     * @param modif un boolean<pre>True si on est en train de modifier une commande </pre> <pre>False sinon </pre>
     */
    public static void livraison(Client c,Commande cmd,List<Article> articles,boolean modif){
        Adresse addr =new Adresse();
        if(LectureClavier.lireOuiNon("Voulez vous être livrée chez vous")){
                cmd.setEstLivreChezClient(true);
                cmd.setAdresseLivraison(c.getNumeroRue(),c.getNomRue(),c.getVille(),c.getCp(),c.getPays());
        }else{
                InterfaceAdresse.choixAdresseLivraison(c,addr);
                cmd.setEstLivreChezClient(false);
                cmd.setAdresseLivraison(addr.getNumeroRue(),addr.getNomRue(),addr.getVille(),addr.getCp(),addr.getPays());
        }
        validationCommande(c, cmd, articles);
    }

    /**
     * Etape de validation de la commande
     * @param c le {@link Client} courant
     * @param cmd la {@link Commande} courante
     * @param articles la List&lt;{@link Article}&gt; des {@link Article} de la commande cmd
     */
    public static void validationCommande(Client c,Commande cmd,List<Article> articles){
        System.out.println("voici le descriptif de votre commande :");
        System.out.println("    " + cmd.toString());
        System.out.println("    Details de vos articles :");
        for (Article a : articles) {
            System.out.println("        " + a.factureString());
        }

        if (LectureClavier.lireOuiNon("valider la commande ? (o/n)")) {
            cmd.setDateCommande(Date.valueOf(LocalDate.now()));
            cmd.setStatus(StatutCommande.EN_COURS);
            if (cmdDao.create(cmd)) {
                for (Article a : articles)
                    articleDAO.create(a);
                System.out.println("Votre commande n°" + cmd.getIdCommande()
                        + " a bien été enregistrer , il ne vous reste plus qu'a payer");
                payment(cmd);
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
                case 1: choixImpression(c, cmd,true);
                        break;
                case 2:InterfaceCodePromo.utilisationCodePromo(c, cmd, articles,true);
                        break;
                case 3:livraison(c, cmd, articles,true);
                        break;
                case 4:break;
            }
        }
    }
    /**
     * cette fonction simule l'etape de payment a un utilisateur
     * et vas mettre à <pre>PRETE_ENVOI le statut de la commande </pre>
     * @param cmd La {@link Commande} qu'on veut payer ou non 
     */
    public static void payment(Commande cmd){
        if(LectureClavier.lireOuiNon("voulez vous payer maintenant ? (o/n)")){
            System.out.println("Voila payer , votre commande et maintenant prete a l'envoie");
            cmd.setStatus(StatutCommande.PRETE_ENVOI);
            cmdDao.update(cmd);
        }else{
            System.out.println("Pas de souci , vous pourrez modifier votre commande tant qu'elle n'est pas payer ! ");
        }
    }
    /**
     * Permet d'afficher les commande d'un {@link Client}
     * @param c le {@link Client} a qui on veut afficher ses commandes
     */
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
    /**
     * Permet d'afficher les détails d'une commande
     * @param c la {@link Commande} pour laquel on veut afficher ses detail
     */
    public static void afficherDetailCommande(Commande c) {
        List<Article> tabArticles = articleDAO.readAllByCommande(c);
        System.out.println(c.toString());
        System.out.println("\n\nLes details de votre commande :");
        if (tabArticles.size() > 0) {
            for (int i = 1; i <= tabArticles.size(); i++) {
                System.out.println("    " + tabArticles.get(i - 1).factureString());
            }
        } else {
            System.out.println("    aucun article dans votre commande !\n");
        }
    }
}
