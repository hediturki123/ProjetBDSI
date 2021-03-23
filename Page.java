import java.sql.Connection;

public class Page extends DAO<Page> {

	private int idPage;
	private String idImpression;
	private String miseEnForme;
	
	public Page(Connection conn, int idPage, String idImpression, String miseEnForme) {
		super(conn);
		this.idPage = idPage;
		this.idImpression = idImpression;
		this.miseEnForme = miseEnForme;
	}

	@Override
	public boolean create(Page obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Page read(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page[] readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Page obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Page obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void InterfacePage() {
		System.out.println("Vous allez ici créer une page");
	}

	
	/***** getters and setters *****/
	
	protected int getIdPage() {
		return idPage;
	}

	protected void setIdPage(int idPage) {
		this.idPage = idPage;
	}

	protected String getIdImpression() {
		return idImpression;
	}

	protected void setIdImpression(String idImpression) {
		this.idImpression = idImpression;
	}

	protected String getMiseEnForme() {
		return miseEnForme;
	}

	protected void setMiseEnForme(String miseEnForme) {
		this.miseEnForme = miseEnForme;
	}
}
