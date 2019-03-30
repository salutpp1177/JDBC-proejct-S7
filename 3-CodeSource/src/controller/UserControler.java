package controller;

import model.Utilisateur;


public class UserControler {

	private Utilisateur modelUser;
	
	public UserControler() {
		this.modelUser = new Utilisateur();
	}
	
	public void signIn(String n, String pm, String username, String mdp) {
		
		modelUser.ajouterUtilisateur(n,pm,username,mdp);
	}
	public Utilisateur getModelUser() {
		return modelUser;
	}

	public void setModelUser(Utilisateur modelUser) {
		this.modelUser = modelUser;
	}

	public int login(String username, String mdp) {	
		if(this.modelUser.verifierUtilisateur(username, mdp) < 1) {
			return -1;
		} else {
			return this.modelUser.verifierUtilisateur(username, mdp);
		}
	}
	
	public boolean verifierUsernameExist(String username) {
		if(this.modelUser.verfierUsername(username) == -1) {
			return false;
		} else {
			return true;
		}
	}

	public void modify(String username, String nom, String prenom) {
		this.modelUser.modifierIdentify(nom, prenom, username);
	}
	
	public void modifyPasswd(String username, String mdp) {
		this.modelUser.modifierPasswd(username, mdp);
	}
}
