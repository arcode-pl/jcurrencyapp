package com.example.jcurrencyapp.ctrl;

import java.time.LocalDate;

import com.example.jcurrencyapp.data.provider.IProvider;
import com.example.jcurrencyapp.data.provider.impl.NbpJsonProvider;
import com.example.jcurrencyapp.model.CurrencyTypes;

/**
 * @author emil.arkita
 *
 */
public class ProviderCtrl {
	private IProvider provider;
	
	public ProviderCtrl() {
		this.provider = new NbpJsonProvider();
	}
	
	public ProviderCtrl(IProvider provider) {
		this.provider = provider;
	}
	
	public IProvider getProvider() {
		return provider;
	}

	public void setProvider(IProvider provider) {
		this.provider = provider;
	}

	public String getData(CurrencyTypes code, LocalDate date, int maxBackDays) {
		int retryCnt = 0;
		String data = null;

		// Loop until valid data or max days reached
		while ( (( data = provider.getData(code.toString(), date) ) == null) && 
				retryCnt < maxBackDays) {
			date = date.minusDays(1);
			retryCnt++;
		}

		return data;
	}
}
