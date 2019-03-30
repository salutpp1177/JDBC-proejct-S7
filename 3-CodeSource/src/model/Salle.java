package model;

import dao.SalleDao;

public class Salle {
	private Integer IdSalle; 
	private String nom;
	
	private SalleDao salledao;
	public Salle() {
		salledao = new SalleDao();
	}
	
	/*
	public int getNbSalle() {
		return salledao.CountNbSalle();
	}
	*/
	
	public Salle(int id, String nom) {
		this.setIdSalle(id);
		this.setNom(nom);
	}

	public Integer getIdSalle() {
		return IdSalle;
	}

	public void setIdSalle(Integer idSalle) {
		IdSalle = idSalle;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	

}
