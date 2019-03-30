package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import model.Temp;

public class TempDao {
	private PreparedStatement ps = null;
	private Connection conn = null;
	private ResultSet rs = null;
	
	public TempDao() {
		
	}
	
	public PreparedStatement getPs() {
		return ps;
	}
	
	public void setPs(String sql) throws SQLException {
		this.ps = (PreparedStatement) this.conn.prepareStatement(sql);
	}
	
	public void inserer(Temp t) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "INSERT INTO TEMP VALUE (NULL,?,?)";
		try {
			this.setPs(sql);
			this.ps.setDate(1, t.getDate());
			this.ps.setTime(2, t.getTime());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
	}
	
	public void modifier(int i, Date d, java.sql.Time t){
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "UPDATE TEMP SET DATE=?, HEURE=? WHERE IDTEMP=?;";
		try {
			this.setPs(sql);
			this.ps.setDate(1, d);
			this.ps.setTime(2, t);
			this.ps.setInt(3, i);
			this.ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Date selectDateParId(int id) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "SELECT Date FROM TEMP WHERE IDTEMP = ?;";
		Date date = null;
		try {
			this.setPs(sql);
			this.ps.setInt(1, id);
			this.rs = this.ps.executeQuery();
			while(this.rs.next()) {
				date = rs.getDate(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
		return date;
	}
	public Temp selectionnerParId(int id) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "SELECT * FROM TEMP WHERE IDTEMP = ?;";
		Temp time = new Temp();
		try {
			this.setPs(sql);
			this.ps.setInt(1, id);
			time.setIdTemp(id);
			this.rs = this.ps.executeQuery();
			while(this.rs.next()) {
				time.setDate(rs.getDate("DATE"));
				time.setTime(rs.getTime("HEURE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
		return time;
	}
	
	public int selectionnerParDatetime(Date d,Time t) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "SELECT idtemp FROM TEMP WHERE DATE = ? and HEURE = ?;";
		int idtemp = 0;
		try {
			this.setPs(sql);
			this.ps.setDate(1, d);
			this.ps.setTime(2, t);
			this.rs = this.ps.executeQuery();
			while(this.rs.next()) {
				idtemp = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
		return idtemp;
	}
	
	
	public List<Temp> SelectinnerParHeure(Time time) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "SELECT * FROM TEMP WHERE HEURE = ?;";
		List<Temp> list = new ArrayList<>();
		try {
			this.setPs(sql);
			this.ps.setTime(1, time);
			this.rs = this.ps.executeQuery();
			Temp t = new Temp();
			while(this.rs.next()) {
				t.setIdTemp(rs.getInt("IDTEMP"));
				t.setDate(rs.getDate("DATE"));
				t.setTime(rs.getTime("HEURE"));
			}
			list.add(t);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
		return list;
	}
	
	public List<Temp> SelectinnerParDate(Date date) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "SELECT * FROM TEMP WHERE DATE = ?;";
		List<Temp> list = new ArrayList<Temp>();
		try {
			this.setPs(sql);
			this.ps.setDate(1, date);
			this.rs = this.ps.executeQuery();
			Temp t = new Temp();
			while(this.rs.next()) {
				t.setIdTemp(rs.getInt("IDTEMP"));
				t.setDate(rs.getDate("DATE"));
				t.setTime(rs.getTime("HEURE"));
			}
			list.add(t);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
		return list;
	}
	
	
	
	
	
}
