package toremove;

public class ParserFactory {

	/*private static final Map<Integer, ParserIntrface<CurrencyType>> PARSERS = Map.ofEntries(
			Map.entry(ParserTypes.XML, new XmlParser()),
			Map.entry(ParserTypes.JSON, new JsonParser()),
			Map.entry(ParserTypes.CSV, new CsvParser()),
			Map.entry(ParserTypes.FILE, new FileParser())
			);

	public static ParserIntrface<CurrencyType> getStrategy(int type) throws WrongProtocolException {
		ParserIntrface<CurrencyType> parser = PARSERS.get(type);
		if (parser == null) {
			throw new WrongProtocolException();
		}
		
		return parser;
	}
	
	public static void exist(int protocol) throws WrongProtocolException {
		if (PARSERS.get(protocol) == null) {
			throw new WrongProtocolException();
		}
	}*/
}
