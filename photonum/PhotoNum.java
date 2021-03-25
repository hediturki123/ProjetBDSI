package photonum;

import java.sql.*;
import photonum.utils.*;
import photonum.interfaces.InterfaceClient;
import photonum.interfaces.InterfaceGestion;

public class PhotoNum {
    private static final String configurationFile = "BD.properties";
    public static Connection conn;

    public static void main(String[] args) {

        try {
            System.out.print("Loading Oracle driver... ");
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("loaded");
            DatabaseAccessProperties dap = new DatabaseAccessProperties(configurationFile);
            System.out.print("Connecting to the database... ");
            conn = DriverManager.getConnection(dap.getDatabaseUrl(), dap.getUsername(),dap.getPassword());
   	        System.out.println("connected");
            conn.setAutoCommit(true);

            int choix = LectureClavier.lireEntier(
                "1. Connexion client\n" +
                "2. Accès gestionnaire\n" + 
                "3. Quitter l'application\n"+
                ">"
            );
            while (choix!=3) {
                while(choix!=2 &&  choix!=1){
                    choix = LectureClavier.lireEntier(
                    "1. Connexion client\n" +
                    "2. Accès gestionnaire\n" + 
                    "3. Quitter l'application\n"+
                    ">"
                );
                }
                switch (choix) {
                    case 1: InterfaceClient.interfaceConnexion(); break;
                    case 2: InterfaceGestion.menuPrincipal();
                        break;
                    case 3:break;
                    default : break;
                }
                choix = LectureClavier.lireEntier(
                    "1. Connexion client\n" +
                    "2. Accès gestionnaire\n" + 
                    "3. Quitter l'application\n"+
                    ">"
                );
                
            }
            System.out.println("Merci de votre visite");

        } catch(SQLException e) {
            System.err.println("failed");
              System.out.println("Affichage de la pile d'erreur");
  	          e.printStackTrace(System.err);
              System.out.println("Affichage du message d'erreur");
              System.out.println(e.getMessage());
              System.out.println("Affichage du code d'erreur");
  	          System.out.println(e.getErrorCode());
        }
    }
}
