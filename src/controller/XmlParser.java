package controller;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import model.NbpCurrency;

public class XmlParser {
	
	public static Document loadXmlFromString(String xml) {
		Document doc = null;
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(new InputSource(new StringReader(xml)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return doc;
	}
	
	public static double getSimpleRate(Document doc, String rateType) {
		NodeList list = doc.getElementsByTagName(rateType);
		if (list.getLength() == 1)
		{
			return Double.valueOf(list.item(0).getTextContent());
		}
		
		return 0;
	}
}
