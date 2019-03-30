package model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.ReservationDao;

public class Reservation {
    private Integer IdSalle;
    private Integer IdTemp;
    private Integer IdUser;
    private List<Salle> salleList;
    private List<Temp> tempList;
    private List<Utilisateur> userList;
    
    private ReservationDao reservationdao;
    
    public Reservation()  {
    	this.salleList = new ArrayList<>();
    	this.tempList = new ArrayList<>();
    	this.userList = new ArrayList<>();
    	reservationdao = new ReservationDao();
    }
    
    public Reservation(int s, int t, int u) {
    	this.setIdSalle(s);
    	this.setIdTemp(t);
    	this.setIdUser(u);
    }

    public void ajouterReservation(String salle, Time temp, Date date, String user) {
    	reservationdao.insertReservation(salle, temp, date, user);
    }
    public void supprimerReservation(String salle,Time temp,Date date, String user) {
    	reservationdao.supprimerReservation(salle, temp, date, user);	
    }
    public HashMap<String, List<String>> getReservation(Date date) {
		return reservationdao.HashReservation(date);
		//return horairedao.SelectHoraire();
	}
    public Integer getIdSalle() {
		return IdSalle;
	}



	public void setIdSalle(Integer idSalle) {
		IdSalle = idSalle;
	}



	public Integer getIdTemp() {
		return IdTemp;
	}



	public void setIdTemp(Integer idTemp) {
		IdTemp = idTemp;
	}



	public Integer getIdUser() {
		return IdUser;
	}



	public void setIdUser(Integer idUser) {
		IdUser = idUser;
	}



	public List<Salle> getSalleList() {
		return salleList;
	}



	public void setSalleList(List<Salle> salleList) {
		this.salleList = salleList;
	}



	public List<Temp> getTempList() {
		return tempList;
	}



	public void setTempList(List<Temp> tempList) {
		this.tempList = tempList;
	}



	public List<Utilisateur> getUserList() {
		return userList;
	}



	public void setUserList(List<Utilisateur> userList) {
		this.userList = userList;
	}


    
}
