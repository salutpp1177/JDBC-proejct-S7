package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.Connection;

import model.Reservation;

public class ReservationDao {

	private PreparedStatement ps = null;
	private Connection conn = null;
	private ResultSet rs = null;
	private UtilisateurDao utilisateurdao;
	private TempDao tempdao;

	public ReservationDao() {
		utilisateurdao = new UtilisateurDao();
		tempdao = new TempDao();
	}

	public PreparedStatement getPs() {
		return ps;
	}

	public void setPs(String sql) throws SQLException {
		this.ps = (PreparedStatement) this.conn.prepareStatement(sql);
	}

	public void insertReservation(String salle, Time temp, Date date,
			String username) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "INSERT INTO RESERVATION VALUE (?,?,?)";
		int idtemp = tempdao.selectionnerParDatetime(date, temp);
		int iduser = utilisateurdao.selectIDParUsername(username);
		try {
			this.setPs(sql);
			this.ps.setString(1, salle);
			this.ps.setInt(2, idtemp);
			this.ps.setInt(3, iduser);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
	}

	public void supprimerReservation(String salle, Time temp, Date date,
			String username) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "DELETE FROM RESERVATION WHERE NOMSALLE = ? AND IDTEMP = ? AND IDUTILISATEUR = ?;";
		int idtemp = tempdao.selectionnerParDatetime(date, temp);
		int iduser = utilisateurdao.selectIDParUsername(username);
		try {
			this.setPs(sql);
			this.ps.setString(1, salle);
			this.ps.setInt(2, idtemp);
			this.ps.setInt(3, iduser);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
	}

	public HashMap<String, List<String>> HashReservation(Date date) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "SELECT * FROM `reservation` RIGHT JOIN horaires on horaires.NOMSALLE = reservation.NOMSALLE AND horaires.IDTEMP = reservation.IDTEMP WHERE horaires.IDTEMP IN (SELECT IDTEMP FROM horaires NATURAL JOIN temp WHERE temp.DATE = ?)ORDER BY  horaires.NOMSALLE,horaires.IDTEMP;";
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();

		try {
			this.setPs(sql);
			this.ps.setDate(1, date);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String key = rs.getString(5);

				if (!map.containsKey(key))
					map.put(key, new LinkedList<String>());

				map.get(key).add(
						utilisateurdao.selectUsernameParID(rs.getInt(3)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
/*
		for (HashMap.Entry<String, List<String>> entry : map.entrySet()) {
			String key = entry.getKey();
			List<String> values = entry.getValue();

		}*/
		JdbcUtil.close(conn, ps, rs);
		return map;
	}

	public List<Reservation> selectionnerParDate(int date) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "SELECT * FROM RESERVATION WHERE IDTEMP = ?;";
		List<Reservation> list = new ArrayList<Reservation>();
		try {
			this.setPs(sql);
			this.ps.setInt(1, date);
			this.rs = this.ps.executeQuery();
			Reservation book = new Reservation();
			while (this.rs.next()) {
				book.setIdSalle(rs.getInt("SALLE"));
				book.setIdTemp(rs.getInt("TEMP"));
				book.setIdUser(rs.getInt("USER"));
				list.add(book);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
		return list;
	}

}