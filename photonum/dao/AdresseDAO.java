package photonum.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import photonum.objects.*;

public class AdresseDAO extends DAO<Adresse>{
	
	/**
	 * Creation de l'objet DAO
	 * @param conn la connection a la base de donnée
	 */
	public AdresseDAO(Connection conn) {
		super(conn);
	}
	
	/**
	 * @param obj une {@link Adresse} de livraison à creer dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean create(Adresse obj) {
		try {
			PreparedStatement requeteCreate=this.connect.prepareStatement(
				"INSERT INTO LesAdressesDeLivraison VALUES (?,?,?,?,?,?)"
			);
			requeteCreate.setString(1, obj.getMailClient());
			requeteCreate.setInt(2, obj.getNumeroRue());
			requeteCreate.setString(3, obj.getNomRue());
			requeteCreate.setString(4, obj.getVille());
			requeteCreate.setInt(5, obj.getCp());
			requeteCreate.setString(6,obj.getPays());

			int reussi=requeteCreate.executeUpdate();
			requeteCreate.close();
			return reussi==1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 
	 * @param id le mail du client
	 * @return La premiere {@link Adresse} de livraison du client
	 * @exception SQLException;
	 */
	@Override
	public Adresse read(Object id) {
		String mail=(String) id;
		Adresse addr;
		try {
			PreparedStatement requeteRead=this.connect.prepareStatement(
				"SELECT * from LesAdressesDeLivraison where mailClient=?"
			);
			requeteRead.setString(1,mail);
			ResultSet resultat=requeteRead.executeQuery();
			if(!resultat.next()){
				addr=null;
			}else{
				addr= new Adresse(
				resultat.getString("mailClient"),
				resultat.getInt("numeroRue"),
				resultat.getString("nomRue"),
				resultat.getString("ville"),
				resultat.getInt("cp"),
				resultat.getString("pays"));
			}
			requeteRead.close();
			return addr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @param obj une {@link Adresse} à update dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean update(Adresse obj) {
		try {
			PreparedStatement requeteUpdate=this.connect.prepareStatement(
				"UPDATE INTO lesAdressesDeLivraison set"+
				"numeroRue=?,"+
				"nomRue=?,"+
				"ville=?,"+
				"cp=?,"+
				"pays=?"+
				"where mailClient=?"
			);
			requeteUpdate.setInt(1, obj.getNumeroRue());
			requeteUpdate.setString(2, obj.getNomRue());
			requeteUpdate.setString(3, obj.getVille());
			requeteUpdate.setInt(4, obj.getCp());
			requeteUpdate.setString(5,obj.getPays());
			requeteUpdate.setString(6,obj.getMailClient());

			int reussi=requeteUpdate.executeUpdate();
			requeteUpdate.close();
			return reussi==1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * @param obj une {@link Adresse} à delete dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean delete(Adresse obj) {
		try {
			PreparedStatement requeteDelete=this.connect.prepareStatement(
				"DELETE FROM lesAdressesDeLivraison where mailClient=?"+
				"and numeroRue=?"+
				"and nomRue=?"+
				"and cp=?"+
				"and ville=?"
			);
			requeteDelete.setString(1, obj.getMailClient());
			requeteDelete.setInt(2, obj.getNumeroRue());
			requeteDelete.setString(3, obj.getNomRue());
			requeteDelete.setInt(4,obj.getCp() );
			requeteDelete.setString(5,obj.getVille());

			int reussi=requeteDelete.executeUpdate();
			requeteDelete.close();
			return reussi==1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @return <b>List&lt;{@link Adresse}&gt;</b> la liste de toutes les adresseDeLivraison de la base
	 * @exception SQLException;
	 */
	@Override
	public List<Adresse> readAll() {
		ArrayList<Adresse> tab =new ArrayList<>();
		try {
			PreparedStatement requeteAll=this.connect.prepareStatement(
				"select * from lesAdressesDeLivraison"
			);
			ResultSet resultat=requeteAll.executeQuery();
			while(resultat.next()){
				tab.add(
					new Adresse(
					resultat.getString("mailClient"),
					resultat.getInt("numeroRue"),
					resultat.getString("nomRue"),
					resultat.getString("ville"),
					resultat.getInt("cp"),
					resultat.getString("pays")
				));
			}
			requeteAll.close();
			return tab;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param c le {@link Client} pour lequel on chercher ses adresses de livraison 
	 * @return <b>List&lt;{@link Adresse}&gt;</b>  la liste de toutes les adresseDeLivraison de la base en fonction du {@link Client}
	 * @exception SQLException;
	 */
	public List<Adresse> readAllByClient(Client c){
		ArrayList<Adresse> tabAdresse = new ArrayList<>();
		try {
			PreparedStatement requeteAllC=this.connect.prepareStatement(
				"SELECT * FROM lesAdressesDeLivraison where mailClient=?"
			);
			requeteAllC.setString(1,c.getMail());
			ResultSet result=requeteAllC.executeQuery();
			while(result.next()){
				tabAdresse.add(
					new Adresse(result.getString("mailClient"),
					result.getInt("numeroRue"),
					result.getString("nomRue"),
					 result.getString("ville"),
					 result.getInt("cp"),
					 result.getString("pays")
				));
			}
			requeteAllC.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tabAdresse;
	}
}
