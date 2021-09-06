package kursy;

import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Application {
	
	public static void main(String[] args) {
		
		System.out.println("EUR [" + getExchangeRate("EUR") + "]");
		System.out.println("JPY [" + getExchangeRate("JPY") + "]");
		System.out.println("KRW [" + getExchangeRate("KRW") + "]");
		
        return;
	}

	public static double getExchangeRate(String currencyCode)
	{
		String xmlRawString = getWebPageBodyAsString("https://www.nbp.pl/kursy/xml/a172z210906.xml");
		Document doc = loadXmlFromString(xmlRawString);
		
		NodeList nodes = doc.getElementsByTagName("pozycja");
		int divider;
		double price;
		boolean found;
		
		for (int idx = 0; idx < nodes.getLength(); idx++) {
			NodeList childNodes = nodes.item(idx).getChildNodes();
			
			found = false;
			divider = 0;
			price = 0;
			
			for (int idxChild = 0; idxChild < childNodes.getLength(); idxChild++) {
				
				//String nodeName = childNodes.item(idxChild).getNodeName();
				//System.out.println(nodeName);
				
				if(childNodes.item(idxChild).getNodeName().equals("kod_waluty")) {
					
					if(childNodes.item(idxChild).getTextContent().equals(currencyCode)) {
						found = true;
						//System.out.println("Found currency: " + currencyCode);
					}
				}
				
				if(childNodes.item(idxChild).getNodeName().equals("przelicznik")) {
					divider = Integer.parseInt(childNodes.item(idxChild).getTextContent());
					//System.out.println("Divider: " + divider);
				}
				
				if(childNodes.item(idxChild).getNodeName().equals("kurs_sredni")) {
					String priceString = childNodes.item(idxChild).getTextContent();
					priceString = priceString.replace(',', '.').replace(" ", "");
					price = Double.valueOf(priceString);
					//System.out.println("Price: " + price);
				}
			}
			
			// Found all needed elements in XML
			if (found && price > 0 && divider > 0) {
				return price / divider;
			}
		}
		
		return 0;
	}
	
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
	
	public static String getWebPageBodyAsString(String url) {
		String text = "";
		
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET() // GET is default
                .build();

			HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
			text = response.body();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return text;
	}
}
