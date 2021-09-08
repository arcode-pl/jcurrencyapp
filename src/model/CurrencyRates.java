package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CurrencyRates {
	String no;
	LocalDate effectiveDate;
	BigDecimal bid;
	BigDecimal ask;
	BigDecimal mid;
	
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
	
	public BigDecimal getBid() {
		return bid;
	}
	
	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}
	
	public BigDecimal getAsk() {
		return ask;
	}
	
	public void setAsk(BigDecimal ask) {
		this.ask = ask;
	}
	
	public BigDecimal getMid() {
		return mid;
	}
	
	public void setMid(BigDecimal mid) {
		this.mid = mid;
	}
}
