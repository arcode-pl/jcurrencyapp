package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.example.jcurrencyapp.data.converter.IConverter;
import com.example.jcurrencyapp.data.converter.impl.NbpJsonConverter;
import com.example.jcurrencyapp.data.converter.impl.NbpXmlConverter;
import com.example.jcurrencyapp.data.provider.IProvider;
import com.example.jcurrencyapp.data.provider.impl.NbpJsonProvider;
import com.example.jcurrencyapp.data.provider.impl.NbpXmlProvider;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

/**
 * @author emil.arkita
 *
 */
public class Controller {
	private List<IProvider> providers;
	private Config config;
	
	public Controller() {
		this.providers = Arrays.asList(
				new NbpJsonProvider(new NbpJsonConverter()),
				new NbpXmlProvider(new NbpXmlConverter()));
		this.config = new Config();
	}

	public Controller(List<IProvider> providers) {
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
		String raw;
		IConverter converter;
		
		// Loop through days
		while (retryCnt <= config.getMaxBackDays()) {

			// Loop through providers to get rate from wanted day
			for (IProvider provider : providers) {
				raw = provider.getData(code, date);
				converter = provider.getConverter();
				
				rate = converter.getRate(raw);
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
