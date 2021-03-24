package photonum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import photonum.objects.CodePromo;

public class CodePromoDAO extends DAO<CodePromo>{

    public CodePromoDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(CodePromo cp) {
		boolean success = false;
		try {
			PreparedStatement pstmt = this.connect.prepareStatement(
				"INSERT INTO LesCodesPromo VALUES (?,?,?)"
			);
			pstmt.setString(1, cp.getMail());
			pstmt.setString(2, cp.getCode());
			pstmt.setBoolean(3, cp.getEstUtilise());
			success = pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

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

	@Override
	public boolean update(CodePromo cp) {
		boolean success = false;
		try {
			PreparedStatement pstmt = this.connect.prepareStatement(
				"UPDATE LesCodesPromo SET mail=?, estUtilise=? WHERE code=?"
			);
			pstmt.setString(1, cp.getMail());
			pstmt.setString(2, cp.getEstUtilise());
			pstmt.setString(3, cp.getCode());
			success = (pstmt.executeUpdate() === 1);
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean delete(CodePromo cp) {
		boolean success = false;
		try {
			PreparedStatement pstmt = this.connect.prepareStatement(
				"DELETE FROM LesCodesPromo WHERE code=?"
			);
			pstmt.setString(1, cp.getCode());
			success = (pstmt.executeUpdate() === 1);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

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
}
