package pl.arcode.jcurrencyapp.data.provider;

import java.time.LocalDate;
import java.util.Optional;

public interface ProviderInterface<T> {
	public Optional<T> getRate(String code, LocalDate date);
}
