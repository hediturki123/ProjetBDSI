package photonum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import photonum.objects.Article;
import photonum.objects.Commande;

public class ArticleDAO extends DAO<Article> {

	/**
	 * construit un ArticleDAO avec la connexions à la BD
	 * @param conn
	 */
    public ArticleDAO(Connection conn) {
		super(conn);
	}

	/**
	 * @param obj un {@link Article} à creer dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean create(Article obj) {
		try {
			PreparedStatement requeteCreate=this.connect.prepareStatement(
				"INSERT INTO lesArticles VALUES (?,?,?)"
			);
			requeteCreate.setInt(1,obj.getIdCommande());
			requeteCreate.setInt(2,obj.getIdImpression());
			requeteCreate.setInt(3,obj.getQuantite());

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
	 * @param id un <b>Int[2]</b> contenant <b>IdCommande</b> et <b>idImpression</b>
	 * @return Le premiere {@link Article} appartenant à la commande <b>IdCommande</b>
	 * @exception SQLException;
	 * */

	@Override
	public Article read(Object id) {
		Article art=null;
		try {
			int[] args = (int []) id;
			PreparedStatement requeteRead=this.connect.prepareStatement(
				"SELECT * from lesArticles where idCommande=? and idImpression=?"
			);
			requeteRead.setInt(1,args[0]);
			requeteRead.setInt(2,args[1]);
			ResultSet result=requeteRead.executeQuery();
			if(result.next()){
				art= new Article(result.getInt("idCommande"),
				result.getInt("idImpression"),
				result.getInt("quantite"));
			}
			requeteRead.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return art;
	}

	/**
	 * @return <b>List&lt;{@link Article}&gt;</b> la liste de tous les {@link Article} de la base
	 * @exception SQLException;
	 */
	@Override
	public List<Article>readAll() {
		ArrayList<Article> tabArticle=new ArrayList<>();
		try {
			PreparedStatement requeteAll=this.connect.prepareStatement(
				"SELECT * from lesArticles"
			);
			ResultSet result=requeteAll.executeQuery();
			while(result.next()){
				tabArticle.add(
					new Article(result.getInt("idCommande"),
				result.getInt("idImpression"),
				result.getInt("quantite"))
				);
			}
			requeteAll.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tabArticle;
	}

	/**
	 * @param obj un {@link Article} à update dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean update(Article obj) {
		try {
			PreparedStatement requeteCreate=this.connect.prepareStatement(
				"UPDATE INTO lesArticles set "+
				"idCommande=?,"+
				"idImpression=?,"+
				"quantite=?"+
				"where idcommande=? and idImpression=?"
			);
			requeteCreate.setInt(1,obj.getIdCommande());
			requeteCreate.setInt(2,obj.getIdImpression());
			requeteCreate.setInt(3,obj.getQuantite());
			requeteCreate.setInt(1,obj.getIdCommande());
			requeteCreate.setInt(2,obj.getIdImpression());

			int reussi=requeteCreate.executeUpdate();
			requeteCreate.close();
			return reussi==1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @param obj un {@link Article} à delete dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean delete(Article obj) {
		try {
			PreparedStatement requeteDelete=this.connect.prepareStatement(
				"DELETE FROM lesArticles where id=commande=? and idImpression=?"
			);
			requeteDelete.setInt(1,obj.getIdCommande());
			requeteDelete.setInt(2,obj.getIdImpression());

			int reussi=requeteDelete.executeUpdate();
			requeteDelete.close();
			return reussi==1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @param c une {@link Commande}pour trouver ces articles correspondant
	 * @return <b>List&lt;{@link Article}&gt; </b> la liste de tous les {@link Article} de la base en fonction du client
	 * @exception SQLException;
	 */
	public List<Article> readAllByCommande(Commande c) {
		ArrayList<Article> tabArticle=new ArrayList<>();
		try {
			PreparedStatement requeteAll=this.connect.prepareStatement(
				"SELECT * from lesArticles where idCommande=?"
			);
			requeteAll.setInt(1, c.getIdCommande());
			ResultSet result=requeteAll.executeQuery();
			while(result.next()){
				tabArticle.add(
					new Article(result.getInt("idCommande"),
				result.getInt("idImpression"),
				result.getInt("quantite"))
				);
			}
			requeteAll.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tabArticle;
	}
}
