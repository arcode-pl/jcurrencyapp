package model;

import java.time.LocalDate;

public class NbpRates {
	String no;
	LocalDate effectiveDate;
	double bid;
	double ask;
	double mid;
	
	public String getNo() {
		return no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
	
	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}
	
	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	public double getBid() {
		return bid;
	}
	
	public void setBid(double bid) {
		this.bid = bid;
	}
	
	public double getAsk() {
		return ask;
	}
	
	public void setAsk(double ask) {
		this.ask = ask;
	}
	
	public double getMid() {
		return mid;
	}
	
	public void setMid(double mid) {
		this.mid = mid;
	}
}
