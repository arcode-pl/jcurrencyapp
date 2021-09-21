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
	private Cache cache;
	
	public Controller() {
		this.providers = Arrays.asList(
				new NbpJsonProvider(new NbpJsonConverter()),
				new NbpXmlProvider(new NbpXmlConverter()));
		this.config = new Config();
		this.cache = new Cache();
	}

	public Controller(List<IProvider> providers) {
		this.providers = providers;
		this.config = new Config();
		this.cache = new Cache();
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
	
	public Controller clearCache() {
		this.cache.clear();
		
		return this;
	}
	
	public Rate getRate(CurrencyTypes code, LocalDate date) {
		BigDecimal rate;
		int retryCnt = 0;
		String raw;
		IConverter converter;
		
		// Loop through days
		while (retryCnt <= config.getMaxBackDays()) {

			// Firstly try return cache value if used and not forced read
			if (config.isUseCache() && !config.isForceRead()) {
				rate = cache.getRate(code, date);
				if (rate != null) { // Return from cache if valid
					return new Rate(rate, date);
				}
			}
			
			// Loop through providers to get rate from wanted day
			for (IProvider provider : providers) {
				raw = provider.getData(code, date);
				converter = provider.getConverter();
				
				rate = converter.getRate(raw);
				if (rate != null) {
					// Update cache if is used
					if (config.isUseCache()) {
						cache.putRate(code, date, rate);
					}
					
					return new Rate(rate, date);
				}
			}
			
			// Try get rate from previous day until max back days reached
			date = date.minusDays(1);
			retryCnt++;
		}
		
		return null; //Can't get rate
	}
}
