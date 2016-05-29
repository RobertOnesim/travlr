package flights1.flights1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.*;


public class Flights {
	private static final String APIKEY="un392534829936867108047975254670";
	
	private static void adauga(JSONObject a, JSONObject b, String property){
		if (property.equals("Carriers"))
			System.out.println(((JSONArray)b.get(property)).size() + " carriers ");
			
		if (!a.containsKey(property))
			a.put(property, new JSONArray());
		if ( b.containsKey(property))
			((JSONArray)a.get(property)).addAll((JSONArray)b.get(property));
	}
	
	private static String rezultateCerere(String url) throws Exception{
		URL obj = new URL(url);
		HttpURLConnection con2 = (HttpURLConnection) obj.openConnection();
		con2.setRequestMethod("GET");
		con2.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded");
		con2.setRequestProperty("Accept", "application/json");
		con2.setRequestProperty("User-Agent", "Mozilla/5.0");
		con2.setFollowRedirects(false);
		int responseCode = con2.getResponseCode();
		//http://partners.api.skyscanner.net/apiservices/pricing/v1.0/ed9055d7ff9e45d49696a9825157faee_ecilpojl_36D903CF76C9C9604227ABC2D6A359E6?apiKey=un392534829936867108047975254670
		//System.out.println("Response Code : " + responseCode);
		String raspunsZboruri=Utilitare.parseInputStream(con2.getInputStream());
		return raspunsZboruri;
	}
	
	public static JSONObject giveSolution(String departureCity, String arrivalCity, String departureDate, Integer adults, Integer children,Integer infants, Integer maxDuration) throws Exception{
		String url = "http://partners.api.skyscanner.net/apiservices/pricing/v1.0";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
		String stringToWrite ="apiKey="+APIKEY+"&"
				+ "currency=EUR"
				+ "&adults="+adults.toString()
				+ "&children="+children.toString()
				+ "&infants="+infants.toString()
				+ "&originplace="+departureCity
				+ "&destinationplace="+arrivalCity
				+ "&outbounddate="+departureDate
				+ "&country=UK"  //???
				+ "&locale=en-GB" //??
				+ "&locationschema=IATA"
				+ "&groupPricing=true";
		writer.write(stringToWrite);
		
				//+ "&inbounddate=2016-06-02");
		writer.flush();
		int responseCode = con.getResponseCode();
		Map<String, List<String>> map = con.getHeaderFields();
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " ,Value : " + entry.getValue());
		}
		String raspuns = Utilitare.parseInputStream(con.getInputStream());
		url=map.get("Location").get(0)+"?apiKey="+APIKEY+"&pageindex=0&pagesize=20";
		if (maxDuration!=null)
			url = url + "&duration=" + ((Integer)(maxDuration*60)).toString();
		Thread.sleep(3000);
		String informatiiZboruri=rezultateCerere(url);
		System.out.println("informatiiZBORURI "+informatiiZboruri);
		JSONObject json = (JSONObject) new JSONParser().parse(informatiiZboruri);
		
		return json;
		//return null;
	}
}
