package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.example.jcurrencyapp.data.provider.NbpJsonProviderImpl;
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
		this.providers = Arrays.asList(new NbpJsonProviderImpl());
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
		int backDaysCounter = 0;

		while (backDaysCounter <= config.getMaxBackDays()) {

			for (Provider provider : providers) {
				rate = provider.getRate(code, date);
				if (rate != null) {
					Rate result = new Rate(code, date, rate);

					providers.stream().filter(p -> providers.indexOf(p) < providers.indexOf(provider))
							.forEach(p -> p.saveRate(result));

//					int currentProviderIdx = providers.indexOf(provider);
//					for (int i = 0 ; i < currentProviderIdx; i++) {
//						providers.get(i).saveRate(result);
//					}

					return result;
				}
			}

			date = date.minusDays(1);
			backDaysCounter++;
		}

		return null;
	}

}
