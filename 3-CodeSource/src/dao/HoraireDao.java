package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import model.Horaire;

public class HoraireDao {
	private PreparedStatement ps = null;
	private Connection conn = null;
	private ResultSet rs = null;
	private TempDao tempdao;

	public HoraireDao() {
		tempdao = new TempDao();
	}

	public void setPs(String sql) throws SQLException {
		this.ps = (PreparedStatement) this.conn.prepareStatement(sql);
	}

	public void inserer(Horaire horaire) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "INSERER INTO HORAIRES VALUE (?,?,?)";
		try {
			this.setPs(sql);
			this.ps.setInt(1, horaire.getIdTemp());
			this.ps.setString(2, horaire.getNomSalle());
			this.ps.setString(2, horaire.getStatus());
			this.ps.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void modifier(String salle, Time temp, Date date, String status) {
		this.conn = (Connection) JdbcUtil.getConn();
		int idtemp = tempdao.selectionnerParDatetime(date, temp);
		String sql = "UPDATE horaires SET STATUS = ? WHERE NOMSALLE = ? AND IDTEMP = ?;";
		try {
			this.setPs(sql);
			this.ps.setString(1, status);
			this.ps.setString(2, salle);
			this.ps.setInt(3, idtemp);
			this.ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
	}

	public List<String> SelectHoraire() {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "select status from horaires where IDSALLE =1;";
		String status = null;
		List<String> listStatus = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				status = rs.getString(1);
				System.out.println(status);

				listStatus.add(status);

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		JdbcUtil.close(conn, ps, rs);
		return listStatus;
	}

	public HashMap<String, List<String>> HashHoraire(Date date) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "SELECT * FROM `horaires` inner join temp on temp.IDTEMP = horaires.IDTEMP WHERE date =? ORDER BY horaires.NOMSALLE,horaires.IDTEMP;";
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();

		try {
			this.setPs(sql);
			this.ps.setDate(1, date);
			rs = ps.executeQuery();
			while (rs.next()) {
				String key = rs.getString(2);

				if (!map.containsKey(key))
					map.put(key, new LinkedList<String>());

				map.get(key).add(rs.getString(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		JdbcUtil.close(conn, ps, rs);
		return map;
	}

}
