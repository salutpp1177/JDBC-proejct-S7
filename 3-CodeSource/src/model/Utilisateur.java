package model;

import dao.UtilisateurDao;

public class Utilisateur {
	private Integer IdUtilisateur;
	private String nom;
	private String prenom;
	private String username;
	private String motdepasse;
	
	UtilisateurDao userdao;


	public Utilisateur() {
		IdUtilisateur = 0;
		userdao = new UtilisateurDao();
	}
	
	public Utilisateur(int id) {
		this.setIdUtilisateur(id);
	}
	
	public Utilisateur(String username, String mdp) {
		this.setMotdepasse(mdp);
		this.setUsername(username);
	}
	
	public Utilisateur(String n, String pm, String username, String mdp) {
		this.setNom(n);
		this.setPrenom(pm);
		this.setUsername(username);
		this.setMotdepasse(mdp);
	}
	
	//AjouterUtilisateur----------------Example----------------
	public void ajouterUtilisateur(String n, String pm, String username, String mdp) {
		Utilisateur user = new Utilisateur(n,pm,username,mdp);
		userdao.inserer(user);
	}
	
	public int verifierUtilisateur(String username, String mdp) {
		Utilisateur user = null;
		user = this.userdao.selectionnerParUsername(username, mdp);
		int id = 0;
		if(user == null ) {
			return -1;
		} else {
			id = user.getIdUtilisateur();
		}
		return id;
	}
	public int verfierUsername(String username) {
		int iduser = -1;
		iduser = this.userdao.selectIDParUsername(username);
		if(this.userdao.selectIDParUsername(username) > 0) {
			return iduser;
		} else {
			return -1;
		}
	}
	
	public void modifierIdentify(String nom, String prenom, String username) {
		userdao.modifierNom(nom, prenom, username);	
	}
	
	public void modifierPasswd(String username, String mdp) {
		userdao.modifierMdp(mdp, username);
	}
	//--------------------------------------------------------------------------//
	public Integer getIdUtilisateur() {
		return IdUtilisateur;
	}
	public void setIdUtilisateur(Integer idUtilisateur) {
		IdUtilisateur = idUtilisateur;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMotdepasse() {
		return motdepasse;
	}
	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}
}
