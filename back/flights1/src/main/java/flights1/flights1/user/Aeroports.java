package flights1.flights1.user;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.URL;


import flights1.flights1.Utilitare;

public class Aeroports {
	public List <String> getAeroportsByCoordinates(){
		JSONObject raspuns=null;
		List <String> codes = new ArrayList<>();
		String https_url ="https://airport.api.aero/airport/nearest/44.4333158365/26.103199949?maxAirports=3&user_key=31281da2f45a68ef51c08a9228b6037d";
		URL obj=null,url=null;
		try {
			url = new URL(https_url);
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			//conn.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded");
			//		conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = conn.getResponseCode();
			String raspunsAeroporturi=Utilitare.parseInputStream(conn.getInputStream());
			raspunsAeroporturi = raspunsAeroporturi.substring(9,raspunsAeroporturi.lastIndexOf(")"));
			System.out.println("raspuns zboruri   "+raspunsAeroporturi);
			raspuns = (JSONObject) new JSONParser().parse(raspunsAeroporturi);
			JSONArray airports = (JSONArray)raspuns.get("airports");
			for (Object airport : airports){
				String codeAndCityName = (String)((JSONObject)airport).get("code") + (String)((JSONObject)airport).get("city");
				codes.add(codeAndCityName);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return codes;
	}
}
