package flights1.flights1.weatherapi.web;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import flights1.flights1.Utilitare;

public class Weather {
	public static String getWeatherNow(String city,String country){
		JSONObject events = null;
		String url = "http://api.openweathermap.org/data/2.5/weather?q="+ city +"," + country + "&APPID=abb6636f14f7d3caf27a74c7b759cedc";
		URL obj;
		try {
			obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = conn.getResponseCode();
			String weatherAnswer=Utilitare.parseInputStream(conn.getInputStream());
			events = (JSONObject) new JSONParser().parse(weatherAnswer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray arr = (JSONArray) events.get("weather");
		
		String result = ((String)((JSONObject)arr.get(0)).get("main"));
		                         

		return result;
	}
	
	public static String getWeatherForecast(String city,Integer dayForecast){
		JSONObject events = null;
		String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q="+city+"&cnt="+dayForecast+"&APPID=abb6636f14f7d3caf27a74c7b759cedc";
		URL obj;
		try {
			obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = conn.getResponseCode();
			String weatherAnswer=Utilitare.parseInputStream(conn.getInputStream());
			events = (JSONObject) new JSONParser().parse(weatherAnswer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray arr = (JSONArray) events.get("list");
		
		String result = ((String)((JSONObject)((JSONArray)((JSONObject)arr.get(dayForecast-1)).get("weather")).get(0)).get("main"));
		return result;
	}
}
