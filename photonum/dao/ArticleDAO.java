package photonum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import photonum.objects.Article;
import photonum.objects.Commande;

public class ArticleDAO  extends DAO<Article> {

    public ArticleDAO(Connection conn) {
		super(conn);
	}

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

	public List<Article>readAllByCommande(Commande c) {
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
