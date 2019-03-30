package controller;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;

import model.Horaire;
import model.Reservation;
import model.Salle;
import model.Temp;
import model.Utilisateur;
import view.*;

public class Controller {

	private SalleReservationView mainview;
	private UserLoginView loginview;
	private HoraireView horaireview;
	private CreateUserView creatview;
	private modifyIdentityView modifyview;

	private Horaire modelHoraire;
	private Salle modelSalle;
	private Temp modelTemp;
	private Reservation modelReservation;


	public Controller() {

		modelHoraire = new Horaire();
		modelSalle = new Salle();
		modelTemp = new Temp();
		modelReservation = new Reservation();

		horaireview = new HoraireView();
		mainview = new SalleReservationView();
	}

	public void PreviousDay(Date today, String username) {
		Date newdate = null;
		newdate = modelTemp.previousDay(today);
		updateView(newdate, username);
	}

	public void NextDay(Date today, String username) {
		Date newdate = null;
		newdate = modelTemp.nextDay(today);
		updateView(newdate, username);
	}

	public void Reserver(String salle, Time temp, Date date, String status,
			String username) {
		modelHoraire.updateStatus(salle, temp, date, status);
		modelReservation.ajouterReservation(salle, temp, date, username);
	}

	public void Annuler(String salle, Time temp, Date date, String status,
			String username) {
		modelHoraire.updateStatus(salle, temp, date, status);
		modelReservation.supprimerReservation(salle, temp, date, username);
	}

	public HashMap<String, List<String>> showHoraire(Date date) {

		return modelHoraire.getSalleStatus(date);
	}

	public HashMap<String, List<String>> showReservation(Date date) {

		return modelReservation.getReservation(date);
	}

	public Date setDate(int id) {
		return modelTemp.selectDate(id);
	}

	public void updateView(Date date, String username) {

		Date today = null;
		if (date == null) {
			today = setDate(1);
		} else {
			today = date;
		}
		if (username == null) {
			horaireview.HoraireSalle(showHoraire(today), today, username);
		} else {
			mainview.SalleReservation(showHoraire(today),
					showReservation(today), today, username);
		}
	}

	public void toLoginUser() {
		loginview = new UserLoginView();
	}

	public void toCreateUser() {
		creatview = new CreateUserView();
	}

	public void toIdentityView(String username) {
		modifyview = new modifyIdentityView(username);
	}

}
