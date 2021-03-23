import java.sql.*;

public class squellete_appli {
    private static final String configurationFile = "BD.properties";
    public static Connection conn;




    public static void main(String[] args) {

        try{
            System.out.print("Loading Oracle driver... "); 
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("loaded");
            DatabaseAccessProperties dap = new DatabaseAccessProperties(configurationFile);
            System.out.print("Connecting to the database... ");
            conn = DriverManager.getConnection(dap.getDatabaseUrl(), dap.getUsername(),dap.getPassword());
   	        System.out.println("connected");
            conn.setAutoCommit(true);

            int choix=LectureClavier.lireEntier("1. Interface Client \n2. Interface Gestion");
            while(choix!=1 && choix!=2){
                choix=LectureClavier.lireEntier("1. Interface Client \n2. Interface Gestion");
            }
            switch(choix){
                case 1 : InferfaceClient.interfaceConnexion();
                        break;
                case 2: break;
            }

        }
        catch(SQLException e){
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
