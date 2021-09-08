package strategy.parser;

import java.util.Map;

import exceptions.WrongProtocolException;
import model.CurrencyType;
import strategy.parser.impl.CsvParser;
import strategy.parser.impl.FileParser;
import strategy.parser.impl.JsonParser;
import strategy.parser.impl.XmlParser;

public class ParserFactory {

	private static final Map<Integer, ParserStrategy<CurrencyType>> PARSERS = Map.ofEntries(
			Map.entry(ParserTypes.XML, new XmlParser()),
			Map.entry(ParserTypes.JSON, new JsonParser()),
			Map.entry(ParserTypes.CSV, new CsvParser()),
			Map.entry(ParserTypes.FILE, new FileParser())
			);

	public static ParserStrategy<CurrencyType> getStrategy(int type) throws WrongProtocolException {
		ParserStrategy<CurrencyType> parser = PARSERS.get(type);
		if (parser == null) {
			throw new WrongProtocolException();
		}
		
		return parser;
	}
	
	public static void exist(int protocol) throws WrongProtocolException {
		if (PARSERS.get(protocol) == null) {
			throw new WrongProtocolException();
		}
	}
}
