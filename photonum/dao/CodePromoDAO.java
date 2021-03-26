package photonum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import photonum.objects.CodePromo;

public class CodePromoDAO extends DAO<CodePromo>{
	/**
	 * construit un CodePromoDAO avec la connexions à la BD
	 * @param conn
	 */
    public CodePromoDAO(Connection conn) {
		super(conn);
	}

	/**
	 * @param obj un {@link CodePromo}  à creer dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean create(CodePromo cp) {
		boolean success = false;
		try {
			PreparedStatement pstmt = this.connect.prepareStatement(
				"INSERT INTO LesCodesPromo VALUES (?,?,?)"
			);
			pstmt.setString(1, cp.getMailClient());
			pstmt.setString(2, System.currentTimeMillis()+"");
			pstmt.setBoolean(3, cp.estUtilise());
			success = pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	/**
	 * @param id un <b>String</b> qui correspond au {@link CodePromo}
	 * @return Le {@link CodePromo}correspondant au paramètre
	 * @exception SQLException;
	 * */
	@Override
	public CodePromo read(Object obj) {
		CodePromo cp = null;
		String code = (String) obj;
		try {
			PreparedStatement pstmt = this.connect.prepareStatement(
				"SELECT * from LesCodesPromo where code=?"
			);
			pstmt.setString(1, code);
			ResultSet r = pstmt.executeQuery();
			if (r.next()) {
				cp = new CodePromo(
					r.getString("code"),
					r.getString("mail"),
					r.getBoolean("estUtilise")
				);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cp;
	}

	/**
	 * @return <b>List&lt;{@link CodePromo}&gt;</b> la liste de tous les codePromo de la base
	 * @exception SQLException;
	 */
	@Override
	public List<CodePromo> readAll() {
		List<CodePromo> lcp = new ArrayList<>();
		try {
			PreparedStatement pstmt = this.connect.prepareStatement(
				"SELECT * from LesCodesPromo"
			);
			ResultSet r = pstmt.executeQuery();
			while (r.next()) {
				CodePromo cp = new CodePromo(
					r.getString("code"),
					r.getString("mail"),
					r.getBoolean("estUtilise")
				);
				lcp.add(cp);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lcp;
	}

	/**
	 * @param obj un {@link CodePromo} à update dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean update(CodePromo cp) {
		boolean success = false;
		try {
			PreparedStatement pstmt = this.connect.prepareStatement(
				"UPDATE LesCodesPromo SET mail=?, estUtilise=? WHERE code=?"
			);
			pstmt.setString(1, cp.getMailClient());
			pstmt.setBoolean(2, cp.estUtilise());
			pstmt.setString(3, cp.getCode());
			success = (pstmt.executeUpdate() == 1);
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
	/**
	 * @param obj un {@link CodePromo} à delete dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean delete(CodePromo cp) {
		boolean success = false;
		try {
			PreparedStatement pstmt = this.connect.prepareStatement(
				"DELETE FROM LesCodesPromo WHERE code=?"
			);
			pstmt.setString(1, cp.getCode());
			success = (pstmt.executeUpdate() == 1);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	/**
	 * @param mail pour trouver  tous les code promo
	 * @return <b>List&lt;{@link CodePromo}&gt; </b> la liste de toutes les codePromo en fonction du client
	 * @exception SQLException;
	 */

	public List<CodePromo> readAllByClient(String mail) {
		List<CodePromo> lcp = new ArrayList<>();
		try {
			PreparedStatement pstmt = this.connect.prepareStatement(
				"SELECT * from LesCodesPromo WHERE mail=?"
			);
			pstmt.setString(1, mail);
			ResultSet r = pstmt.executeQuery();
			while (r.next()) {
				CodePromo cp = new CodePromo(
					r.getString("code"),
					r.getString("mail"),
					r.getBoolean("estUtilise")
				);
				lcp.add(cp);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lcp;
	}
	/**
	 * Ici on vas preciser l'utilisation du {@link CodePromo}
	 * @param mail pour trouver  tous les code promo
	 * @param estUtilise un boolean qui dit si le code promo est utilisé ou non
	 * @return <b>List&lt;{@link CodePromo}&gt; </b> la liste de tous les codePromo en fonction du client et si il est utiliser ou non
	 * @exception SQLException;
	 */
	public List<CodePromo> readAllByClient(String mail,boolean estUtilise) {
		List<CodePromo> lcp = new ArrayList<>();
		try {
			PreparedStatement pstmt = this.connect.prepareStatement(
				"SELECT * from LesCodesPromo WHERE mail=? and estUtilise=?"
			);
			pstmt.setString(1, mail);
			pstmt.setBoolean(2, estUtilise);
			ResultSet r = pstmt.executeQuery();
			while (r.next()) {
				CodePromo cp = new CodePromo(
					r.getString("code"),
					r.getString("mail"),
					r.getBoolean("estUtilise")
				);
				lcp.add(cp);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lcp;
	}
}
