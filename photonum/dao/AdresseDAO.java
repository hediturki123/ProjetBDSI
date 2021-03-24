package photonum.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import photonum.Adresse;
import photonum.Client;

public class AdresseDAO extends DAO<Adresse>{
	public AdresseDAO(Connection conn) {
		super(conn);
	}
	

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

	@Override
	public Adresse read(Object id) {
		/*
			ici on vas avoir un probleme si le client a plusieur adresse ...
			ici id = le mail du client = String  
		*/
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
		/*
		ici je regarde toutes info pour etre sur qu'on supprime une seul adresse
		a ce niveau , on est sur que l'adresse est unique 
		*/
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

	@Override
	public Adresse[] readAll(Object obj) {
		Client c = (Client) obj;
		ArrayList<Adresse> tab =new ArrayList<>();
		try {
			PreparedStatement requeteAll=this.connect.prepareStatement(
				"select * from lesAdressesDeLivraison where mailClient=?"
			);
			requeteAll.setString(1, c.getMail());
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
			return (Adresse []) tab.toArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
