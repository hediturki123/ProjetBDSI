package photonum.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import photonum.objects.Client;


public class ClientDAO extends DAO<Client> {

	public ClientDAO(Connection conn){
		super(conn);
	}
	
	

	@Override
	public boolean create(Client obj) {

		try {
			PreparedStatement requeteAjout=this.connect.prepareStatement(
					"INSERT INTO LesClients VALUES (?,?,?,?,?,?,?,?,?)");
					requeteAjout.setString(1, obj.getMail());
					requeteAjout.setString(2, obj.getNom());
					requeteAjout.setString(3, obj.getPrenom());
					requeteAjout.setString(4, obj.getMdp());
					requeteAjout.setInt(5, obj.getNumeroRue());
					requeteAjout.setString(6, obj.getNomRue());
					requeteAjout.setString(7, obj.getVille());
					requeteAjout.setInt(8,obj.getCp());
					requeteAjout.setString(9, obj.getPays());
			
			boolean reussi=requeteAjout.execute();
			requeteAjout.close();
			return reussi;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Client read(Object obj) {
		try {
			String [] args=(String[]) obj;
			Client c;
			PreparedStatement requete_selection=this.connect.prepareStatement(
					"SELECT * from LesClients where mail=? and mdp=?");
			requete_selection.setString(1,args[0]); // icic l'obj sera l'adresse mail
			requete_selection.setString(2,args[1]);
			ResultSet resultat=requete_selection.executeQuery();
			if(!resultat.next()){
				c=null;
			} //ici last renvoie false si il n'y a aucune row selectionner
			else{
			 c= new Client
			(
			resultat.getString("mail"),
			resultat.getString("nom"),
			resultat.getString("prenom"),
			resultat.getString("mdp"),
			resultat.getInt("numeroRue"),
			resultat.getString("nomRue"),
			resultat.getString("ville"),
			resultat.getInt("cp"),
			resultat.getString("pays"));
			}
			requete_selection.close();
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Client obj) {
		try {
			PreparedStatement requeteUpdate=this.connect.prepareStatement(
			"UPDATE LesClients set"+
			"mdp=?,"+
			"nom=?,"+
			"prenom=?,"+
			"numeroRue=?,"+
			"nomRue=?,"+
			"ville=?,"+
			"cp=?,"+
			"pays=?"+
			"where mail=?");
			requeteUpdate.setString(1, obj.getMail());
			requeteUpdate.setString(2, obj.getNom());
			requeteUpdate.setString(3, obj.getPrenom());
			requeteUpdate.setString(4, obj.getMdp());
			requeteUpdate.setInt(5, obj.getNumeroRue());
			requeteUpdate.setString(6, obj.getNomRue());
			requeteUpdate.setString(7, obj.getVille());
			requeteUpdate.setInt(8,obj.getCp());
			requeteUpdate.setString(9, obj.getPays());
			int reussi= requeteUpdate.executeUpdate();
			requeteUpdate.close();
			return reussi ==1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Client obj) {
		try {
			PreparedStatement requete_delette=this.connect.prepareStatement(
				"DELETE FROM LesClients where mail=?"
			);
			requete_delette.setString(1,obj.getMail());
			int reussi=requete_delette.executeUpdate();
			requete_delette.close();
			return reussi==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Client> readAll() {
		ArrayList<Client> c= new ArrayList<Client>();
		try{
		PreparedStatement requete_all=this.connect.prepareStatement(
			"SELECT * FROM lesClients");
			ResultSet resultat=requete_all.executeQuery();
			while(resultat.next()){
				 c.add(
					new Client
					(
					resultat.getString("mail"),
					resultat.getString("nom"),
					resultat.getString("prenom"),
					resultat.getString("mdp"),
					resultat.getInt("numeroRue"),
					resultat.getString("nomRue"),
					resultat.getString("ville"),
					resultat.getInt("cp"),
					resultat.getString("pays")));
			}
			requete_all.close();
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**************************************/

}
