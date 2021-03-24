package photonum;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FichierImage extends DAO<FichierImage> {
	private String chemin;
	private String mailProprio;
	private String infoPVD;
	private int resolution;
	private boolean estPartage;
	private Date dateUpload;

	public FichierImage(Connection conn) {
		super(conn);
	}

	public FichierImage(Connection conn, String chemin, String mailProprio, String infoPVD, int resolution,
			boolean estPartage, Date dateUpload) {
		super(conn);
		setAll(chemin, mailProprio, infoPVD, resolution, estPartage, dateUpload);
	}
	

	@Override
	public boolean create(FichierImage obj) {
		try{
			PreparedStatement requeteAjout=this.connect.prepareStatement(
				"INSERT INTO LesFichiersImage values (?,?,?,?,?,?)");
				requeteAjout.setString(1, obj.getChemin());
				requeteAjout.setString(2, obj.getMailProprio());
				requeteAjout.setString(3, obj.getInfoPVD());
				requeteAjout.setInt(4, obj.getResolution());
				requeteAjout.setInt(5, obj.isEstPartage() ? 0:1);
				requeteAjout.setDate(6, obj.getDateUpload());
				

			boolean reussi=requeteAjout.execute();
			requeteAjout.close();
			setAll(obj.chemin, obj.mailProprio, obj.infoPVD, obj.resolution, obj.estPartage, obj.dateUpload);
			return reussi;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public FichierImage read(Object id) {
		String [] args = (String []) id;
		FichierImage img;
		try {
			PreparedStatement requete_read=this.connect.prepareStatement(
			"SELECT * FROM LesFichiersImage where mailProprio=? and chemin=?");
			requete_read.setString(1, args[0]);
			requete_read.setString(2, args[1]);
			ResultSet resultat=requete_read.executeQuery();
			if(!resultat.next()){
				img=null;
			}else{
				img=new FichierImage(this.connect,
				resultat.getString("chemin"),
				resultat.getString("mailProprio"), 
				resultat.getString("infoPVD"), 
				resultat.getInt("resolution"), 
				resultat.getInt("estPartage")==0?true:false, 
				resultat.getDate("dateUpload"));
			}
			requete_read.close();
			return img;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(FichierImage obj) {
		try {
			PreparedStatement requeteUpdate=this.connect.prepareStatement(
				"UPDATE INTO LesFichiersImage set"+
				"chemin=?,"+
				"mailProprio=?,"+
				"infoPvd=?,"+
				"resolution=?,"+
				"estPartage=?,"+
				"dateUpload=?"+
				"where mailProprio=? and chemin=?");
			requeteUpdate.setString(1, obj.getChemin());
			requeteUpdate.setString(2, obj.getMailProprio());
			requeteUpdate.setString(3, obj.getInfoPVD());
			requeteUpdate.setInt(4, obj.getResolution());
			requeteUpdate.setInt(5, obj.isEstPartage() ? 0:1);
			requeteUpdate.setDate(6, obj.getDateUpload());
			
			int reussi=requeteUpdate.executeUpdate();
			requeteUpdate.close();
			return reussi==1;

		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return false;
	}

	@Override
	public boolean delete(FichierImage obj) {
		try {
			PreparedStatement requeteSuppr=this.connect.prepareStatement(
				"DELETE FROM lesFichiersImage where mailProprio=? and chemin=?");
				requeteSuppr.setString(1, obj.getMailProprio());
				requeteSuppr.setString(2, obj.getChemin());
			int reussi=requeteSuppr.executeUpdate();
			requeteSuppr.close();
			return reussi==1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public FichierImage[] readAll() {
		ArrayList<FichierImage> tab=new ArrayList<>();
		try {
			PreparedStatement requeteAll=this.connect.prepareStatement(
				"select * from LesFichiersImage");
			ResultSet resultat= requeteAll.executeQuery();
			while(resultat.next()){
				tab.add(
					new FichierImage(this.connect, 
					resultat.getString("chemin"), 
					resultat.getString("mailProprio"), 
					resultat.getString("infoPVD"), 
					resultat.getInt("resolution"), 
					resultat.getInt("estPartage")==1 ? true:false, 
					resultat.getDate("dateUpload")
				));
			}
			requeteAll.close();
			return (FichierImage[]) tab.toArray();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/***********setter and getter  ***********/
	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public String getMailProprio() {
		return mailProprio;
	}

	public void setMailProprio(String mailProprio) {
		this.mailProprio = mailProprio;
	}

	public String getInfoPVD() {
		return infoPVD;
	}

	public void setInfoPVD(String infoPVD) {
		this.infoPVD = infoPVD;
	}

	public int getResolution() {
		return resolution;
	}

	public void setResolution(int resolution) {
		this.resolution = resolution;
	}

	public boolean isEstPartage() {
		return estPartage;
	}

	public void setEstPartage(boolean estPartage) {
		this.estPartage = estPartage;
	}

	public Date getDateUpload() {
		return dateUpload;
	}

	public void setDateUpload(Date dateUpload) {
		this.dateUpload = dateUpload;
	}
	private void setAll(String chemin, String mailProprio, String infoPVD, int resolution,
	boolean estPartage, Date dateUpload){
		setChemin(chemin);
		setMailProprio(mailProprio);
		setInfoPVD(infoPVD);
		setResolution(resolution);
		setEstPartage(estPartage);
		setDateUpload(dateUpload);
	}
	/*********************************************/
}
