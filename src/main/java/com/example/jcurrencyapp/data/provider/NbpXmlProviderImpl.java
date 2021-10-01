package com.example.jcurrencyapp.data.provider;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.jcurrencyapp.data.converter.Converter;
import com.example.jcurrencyapp.data.converter.nbp.NbpParams;
import com.example.jcurrencyapp.data.converter.nbp.NbpXmlConverterImpl;
import com.example.jcurrencyapp.io.webapi.NbpWebApiRequest;
import com.example.jcurrencyapp.io.webapi.WebApiController;
import com.example.jcurrencyapp.io.webapi.model.WebApiResponse;
import com.example.jcurrencyapp.model.CurrencyTypes;
import com.example.jcurrencyapp.model.Rate;

public class NbpXmlProviderImpl implements Provider { // is implements need here?

	Converter converter;

	public NbpXmlProviderImpl() {
		this.converter = new NbpXmlConverterImpl();
	}

	@Override
	public BigDecimal getRate(CurrencyTypes code, LocalDate date) {

		WebApiResponse response = WebApiController.tryReadApi(NbpWebApiRequest.getXmlQuery(code, date));
		if (response.getCode() == 200) {
			return converter.getRate(response.getText());
		}

		return null;
	}

	@Override
	public void saveRate(Rate rate) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Rate> getRates(CurrencyTypes code, LocalDate startDate, LocalDate endDate) {

		List<Rate> rates = new ArrayList<Rate>();

		if (startDate.isBefore(NbpParams.START_DATE)) {
			startDate = NbpParams.START_DATE;
		}

		// Get full tables NbpAPI - limitation: max one year date range per call,
		// minimal date is 2002 year, max is today
		while (startDate.isBefore(endDate)) {
			LocalDate newEndDate = startDate.plusYears(1);
			if (newEndDate.isAfter(endDate)) {
				newEndDate = endDate;
			}

			WebApiResponse response = WebApiController
					.tryReadApi(NbpWebApiRequest.getXmlQuery(code, startDate, newEndDate));
			if (response.getCode() == 200) {
				rates.addAll(converter.getRates(response.getText()));
			}

			startDate = newEndDate;
		}

		return rates;
	}

	@Override
	public void saveRates(List<Rate> rates) {
		// TODO Auto-generated method stub

	}

}
