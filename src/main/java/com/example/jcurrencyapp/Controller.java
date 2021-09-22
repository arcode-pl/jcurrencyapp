package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.example.jcurrencyapp.data.converter.nbp.NbpJsonConverterImpl;
import com.example.jcurrencyapp.data.converter.nbp.NbpXmlConverterImpl;
import com.example.jcurrencyapp.data.provider.NbpProviderImpl;
import com.example.jcurrencyapp.data.provider.Provider;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

/**
 * @author emil.arkita
 *
 */
public class Controller {
	private List<Provider> providers;
	private Config config;
	
	public Controller() {
		this.providers = Arrays.asList(
				new NbpProviderImpl(new NbpJsonConverterImpl()),
				new NbpProviderImpl(new NbpXmlConverterImpl()));
		this.config = new Config();
	}

	public Controller(List<Provider> providers) {
		this.providers = providers;
		this.config = new Config();
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
	
	public Rate getRate(CurrencyTypes code, LocalDate date) {
		BigDecimal rate;
		int retryCnt = 0;
		
		// Loop through days
		while (retryCnt <= config.getMaxBackDays()) {

			// Loop through providers to get rate from wanted day
			for (Provider provider : providers) {
				rate = provider.getRate(code, date);
				if (rate != null) {
					return new Rate(code, date, rate);
				}
			}
			
			// Try get rate from previous day until max back days reached
			date = date.minusDays(1);
			retryCnt++;
		}
		
		return null; //Can't get rate
	}
}
