package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;

import dao.TempDao;

public class Temp {
	private Integer IdTemp;
	private Date date;
	private Time time;

	private TempDao tempdao;

	public Temp() {
		tempdao = new TempDao();
	}

	public Temp(Date date, Time t) {
		this.setDate(date);
		this.setTime(t);
		tempdao = new TempDao();
	}

	public Integer getIdTemp() {
		return IdTemp;
	}

	public void setIdTemp(Integer idTemp) {
		IdTemp = idTemp;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	
	public Date selectDate(int id) {
		return tempdao.selectDateParId(id);
	}

	public Date nextDay(Date today) {

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(today);
		calendar.add(Calendar.DATE, -6);
		
		java.util.Date utilDate = (java.util.Date) calendar.getTime();
		calendar.add(Calendar.DATE, 7);
		utilDate = (java.util.Date) calendar.getTime();
		
		Date newDate = new Date(utilDate.getTime());
		return newDate;
	}
	
	public Date previousDay(Date today) {

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(today);
		calendar.add(Calendar.DATE, -7);
		
		java.util.Date utilDate = (java.util.Date) calendar.getTime();
		calendar.add(Calendar.DATE, 6);
		utilDate = (java.util.Date) calendar.getTime();
		
		Date newDate = new Date(utilDate.getTime());
		return newDate;
	}
}
