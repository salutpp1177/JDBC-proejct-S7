package model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.HoraireDao;
import dao.TempDao;

public class Horaire {
	private Integer IdTemp;
	private String NomSalle;
	private String status;
	
	private List<Temp> tempList;
	private List<Salle> salleList;
	private List<String> Liststatus; 

	HoraireDao horairedao;
	TempDao tempdao;
	public Horaire() {
	   	this.salleList = new ArrayList<>();
    	this.tempList = new ArrayList<>();
		Liststatus = new ArrayList<>();
		horairedao = new HoraireDao();
		tempdao = new TempDao();
	}

	public void addStatus(String status) {
		Liststatus.add(status);
	}

	public void getStatus(List<String> liststatus) {
		this.Liststatus = liststatus;
	}
	//-----------------------------------------------------//
	public HashMap<String, List<String>> getSalleStatus(Date date) {
		return horairedao.HashHoraire(date);
		
	}
	public void updateStatus(String salle,Time temp,Date date,String status) {
		horairedao.modifier(salle, temp,date,status);
	}
	public void insererStatus(Horaire horaire) {
		horairedao.inserer(horaire);
	}
	
	public Integer getIdTemp() {
		return IdTemp;
	}
	public void setIdTemp(Integer idTemp) {
		IdTemp = idTemp;
	}
	public String getNomSalle() {
		return NomSalle;
	}
	public void setNomSalle(String nom) {
		NomSalle = nom;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Temp> getTempList() {
		return tempList;
	}
	public void setTempList(List<Temp> tempList) {
		this.tempList = tempList;
	}
	public List<Salle> getSalleList() {
		return salleList;
	}
	public void setSalleList(List<Salle> salleList) {
		this.salleList = salleList;
	}
	public List<String> getListstatus() {
		return Liststatus;
	}
	public void setListstatus(List<String> liststatus) {
		Liststatus = liststatus;
	}
	public HoraireDao getHorairedao() {
		return horairedao;
	}
	public void setHorairedao(HoraireDao horairedao) {
		this.horairedao = horairedao;
	}

}
