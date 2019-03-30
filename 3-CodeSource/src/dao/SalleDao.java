package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.mysql.jdbc.Connection;

import model.Salle;

public class SalleDao {
	private PreparedStatement ps = null;
	private Connection conn = null;
	private ResultSet rs = null;
	
	public SalleDao() {
		
	}
	
	public PreparedStatement getPs() {
		return ps;
	}
	
	public void setPs(String sql) throws SQLException {
		this.ps = (PreparedStatement) this.conn.prepareStatement(sql);
	}
	

	
	public void inserer(String s) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "INSERT INTO SALLES VALUE (NULL,?)";
		try {
			this.setPs(sql);
			this.ps.setString(1, s);
			this.ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
	}
	
	public void modifier(String old, String newname){
		this.conn = (Connection) JdbcUtil.getConn();
		Salle sal = new Salle();
		sal = selectionnerParNom(old);
		String sql = "UPDATE SALLES SET NOMSALLE = ? WHERE IDSALLE = ?;";
		try {
			this.setPs(sql);
			this.ps.setString(1, newname);
			this.ps.setInt(2, sal.getIdSalle());
			this.ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Salle selectionnerParId(int id) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "SELECT * FROM SALLES WHERE IDSALLE = ?;";
		Salle salle = new Salle();
		try {
			this.setPs(sql);
			this.ps.setInt(1, id);
			salle.setIdSalle(id);
			this.rs = this.ps.executeQuery();
			while(this.rs.next()) {
				salle.setNom(rs.getString("NOMSALLE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
		return salle;
	}
	
	public Salle selectionnerParNom(String s) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "SELECT * FROM SALLES WHERE NOMSALLE = ?;";
		Salle salle = new Salle();
		try {
			this.setPs(sql);
			this.ps.setString(1, s);
			salle.setNom(s);
			this.rs = this.ps.executeQuery();
			while(this.rs.next()) {
				salle.setIdSalle(rs.getInt("IDSALLE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
		return salle;
	}
	/*
	public int CountNbSalle() {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "SELECT COUNT(*) FROM `salles`;";
		int NbSalle = 0;
		try {
			this.setPs(sql);
			this.rs = this.ps.executeQuery();
			while(this.rs.next()) {
				NbSalle = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
		return NbSalle;
	}*/
	
}
