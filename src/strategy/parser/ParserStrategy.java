package strategy.parser;

import java.util.List;

public interface ParserStrategy<T> {
	public List<T> parse(String rawData);
}
