package com.example.jcurrencyapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.jcurrencyapp.data.provider.Provider;
import com.example.jcurrencyapp.data.provider.nbp.NbpJsonProviderImpl;
import com.example.jcurrencyapp.model.CountryTypes;
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

	private void saveRates(List<Rate> rates, int currenctProviderIdx) {
		providers.stream().filter(p -> providers.indexOf(p) < currenctProviderIdx).forEach(p -> p.saveRates(rates));
	}

	public Rate getPrice(CurrencyTypes code, LocalDate date) {
		BigDecimal rate;
		int backDaysCounter = 0;

		while (backDaysCounter <= config.getMaxBackDays()) {

			for (Provider provider : providers) {
				rate = provider.getPrice(code, date);
				if (rate != null) {
					Rate result = new Rate(code, date, rate);

					providers.stream().filter(p -> providers.indexOf(p) < providers.indexOf(provider))
							.forEach(p -> p.saveRate(result));

					return result;
				}
			}

			date = date.minusDays(1);
			backDaysCounter++;
		}

		return null;
	}

	public CurrencyTypes getMostUnstableCurrency(LocalDate startDate, LocalDate endDate) {
		// loop on currencies to get for each one date of range from available
		// providers???
		// check for count of rates?? or something else? to check all data valid

		BigDecimal maxPriceDifference = new BigDecimal("0.0");
		BigDecimal tmpPriceDifference;
		CurrencyTypes currency = null;

		for (CurrencyTypes var : CurrencyTypes.values()) {
			for (Provider provider : providers) {
				List<Rate> rates = provider.getRates(var, startDate, endDate);

				if (rates != null && rates.size() > 0) {
					rates.sort(new Comparator<Rate>() {
						@Override
						public int compare(Rate r1, Rate r2) {
							return r1.getPrice().compareTo(r2.getPrice());
						}
					});

					// update cache for next usage,
					// maybe better update cache on application startup with all available data?
					// focus on delay for data
					saveRates(rates, providers.indexOf(provider));

					tmpPriceDifference = rates.get(rates.size() - 1).getPrice().subtract(rates.get(0).getPrice());
					if (tmpPriceDifference.compareTo(maxPriceDifference) > 0) {
						maxPriceDifference = tmpPriceDifference;
						currency = var;
					}

					System.out.println("Currency: " + var + ", difference: " + tmpPriceDifference);

					break;
				}
			}
		}

		return currency;
	}

	public Rate getMinRateInDateRange(CurrencyTypes currency, LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	public Rate getMaxRateInDateRange(CurrencyTypes currency, LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Rate> getBestAskRates(CurrencyTypes currency, long quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Rate> getBestBidRates(CurrencyTypes currency, long quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	public CountryTypes findCountriesWithMultipleCurrency() {
		// TODO Auto-generated method stub
		return null;
	}
}
