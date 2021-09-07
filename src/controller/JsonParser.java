package controller;

public class JsonParser {
 
    public double getSimpleRate(String json, String rateType) {
    	String temp = "\"" + rateType + "\":";
    	
        int startIdx = json.indexOf(temp) + temp.length();
        
        String stringValue = json.substring(startIdx);
        stringValue = stringValue.substring(0, stringValue.indexOf("}"));
        
        System.out.println(stringValue);
        
        return Double.valueOf(stringValue);
    }
}
