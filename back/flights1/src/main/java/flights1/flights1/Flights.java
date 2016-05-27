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
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.*;


public class Flights {
	private static final String APIKEY="un392534829936867108047975254670";
	
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
	
	public static JSONObject giveSolution() throws Exception{
		String url = "http://partners.api.skyscanner.net/apiservices/pricing/v1.0";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
		writer.write("apiKey="+APIKEY+"&"
				+ "currency=GBP&"
				+ "adults=1"
				+ "&originplace=OTP&"
				+ "destinationplace=FRA&"
				+ "outbounddate=2016-05-31&"
				+ "country=UK&"
				+ "locale=en-GB&"
				+ "locationschema=IATA&"
				+ "inbounddate=2016-06-02");
		writer.flush();
		int responseCode = con.getResponseCode();
		Map<String, List<String>> map = con.getHeaderFields();
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " ,Value : " + entry.getValue());
		}
		String raspuns = Utilitare.parseInputStream(con.getInputStream());
		url=map.get("Location").get(0)+"?apiKey="+APIKEY+"&pageindex=0&pagesize=20";
		String informatiiZboruri=rezultateCerere(url);
		JSONObject json = (JSONObject) new JSONParser().parse(informatiiZboruri);
		return json;
	}
}
