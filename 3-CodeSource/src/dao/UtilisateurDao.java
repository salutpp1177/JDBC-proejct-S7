package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import model.Utilisateur;

public class UtilisateurDao {

	private PreparedStatement ps = null;
	private Connection conn = null;
	private ResultSet rs = null;
	
	public UtilisateurDao() {
		
	}
	
	public PreparedStatement getPs() {
		return ps;
	}
	
	
	public void setPs(String sql) throws SQLException {
		this.ps = (PreparedStatement) this.conn.prepareStatement(sql);
	}
	
	public void inserer(Utilisateur user) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "INSERT INTO UTILISATEURS VALUE (NULL,?,?,?,?)";
		try {
			this.setPs(sql);
			this.ps.setString(1, user.getNom());
			this.ps.setString(2, user.getPrenom());
			this.ps.setString(3, user.getUsername());
			this.ps.setString(4, user.getMotdepasse());
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
	}
	
	
	/*
	 * @param nom : new name
	 * @param prenom : new prenom
	 * @param username 
	 */
	public void modifierNom(String nom, String prenom, String username) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "UPDATE UTILISATEURS SET NOM = ?, PRENOM = ?  WHERE NOMUTILISATEUR = ?;";
		int num = 0;
		try {
			this.setPs(sql);
			this.ps.setString(1, nom);
			this.ps.setString(2, prenom);
			this.ps.setString(3, username);
			this.ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
	}
	
	
	/*
	 * @param mdp : new password
	 * @param username 
	 */
	public void modifierMdp(String mdp, String username) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "UPDATE UTILISATEURS SET MOTDEPASSE=? WHERE NOMUTILISATEUR = ?;";
		try {
			this.setPs(sql);
			this.ps.setString(1, mdp);
			this.ps.setString(2, username);
			this.ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
	}
	
	/*
	 * select a line of table UTILISATEUR by IDUTILISATEUR
	 */
	public Utilisateur selectionnerParId(int id) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "SELECT * FROM UTILISATEURS WHERE IDUTILISATEUR = ?;";
		Utilisateur user = new Utilisateur();
		try {
			this.setPs(sql);
			this.ps.setInt(1, id);
			user.setIdUtilisateur(id);;
			this.rs = this.ps.executeQuery();
			while(this.rs.next()) {
				user.setNom(rs.getString("NOM"));
				user.setPrenom(rs.getString("PRENOM"));
				user.setUsername(rs.getString("NOMUTILISATEUR"));
				user.setMotdepasse(rs.getString("MOTDEPASSE"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
		return user;
	}
	
	public int selectIDParUsername(String username) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "SELECT IDUTILISATEUR FROM UTILISATEURS WHERE NOMUTILISATEUR = ?;";
		int iduser = 0;
		try {
			this.setPs(sql);
			this.ps.setString(1, username);
	
			this.rs = this.ps.executeQuery();
			while(this.rs.next()) {
				iduser = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
		return iduser;
	}
	
	public String selectUsernameParID(int id) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "SELECT NOMUTILISATEUR FROM UTILISATEURS WHERE IDUTILISATEUR = ?;";
		String username = null;
		try {
			this.setPs(sql);
			this.ps.setInt(1, id);
	
			this.rs = this.ps.executeQuery();
			while(this.rs.next()) {
				username = rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
		return username;
	}
	
	/*
	 * select a line of table UTILISATEUR by USERNAME and PASSWORD
	 */
	public Utilisateur selectionnerParUsername(String username, String mdp) {
		this.conn = (Connection) JdbcUtil.getConn();
		String sql = "SELECT * FROM UTILISATEURS WHERE NOMUTILISATEUR = ? AND MOTDEPASSE = ?;";
		//Utilisateur user = new Utilisateur();
		Utilisateur user = null;
		try {
			this.setPs(sql);
			this.ps.setString(1, username);
			this.ps.setString(2, mdp);
			this.rs = this.ps.executeQuery();
			if (rs!=null) {
				user = new Utilisateur();
				while(this.rs.next()) {
					user.setIdUtilisateur(rs.getInt("IDUTILISATEUR"));
					user.setNom(rs.getString("NOM"));
					user.setPrenom(rs.getString("PRENOM"));
					user.setUsername(rs.getString("NOMUTILISATEUR"));
					user.setMotdepasse(rs.getString("MOTDEPASSE"));
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, ps, rs);
		return user;
	}
	
	


}
