package main;

import exceptions.WrongApiException;
import exceptions.WrongProtocolException;
import strategy.api.ApiFactory;
import strategy.api.ApiTypes;
import strategy.parser.ParserFactory;
import strategy.parser.ParserTypes;

public class AppConfig {
	private int api;
	private int parser;
	
	public AppConfig() {
		this.api = ApiTypes.NBP;
		this.parser = ParserTypes.JSON;
	}
	
	public AppConfig(int api, int protocol) throws WrongProtocolException, WrongApiException {
		ApiFactory.exist(api);
		ParserFactory.exist(protocol);
		
		this.api = api;
		this.parser = protocol;
	}
	
	public int getParser() {
		return parser;
	}
	
	public void setParser(int parser) throws WrongProtocolException {
		ParserFactory.exist(parser);
		this.parser = parser;
	}
	
	public int getApi() {
		return api;
	}
	
	public void setApi(int api) throws WrongApiException {
		ApiFactory.exist(api);
		this.api = api;
	}
}
