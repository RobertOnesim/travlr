package flights1.flights1.user;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import flights1.flights1.Utilitare;

public class IPProcessor {
	public static List<Double> getCoordinates(String userIP){
		String http_url ="http://ip-api.com/json/"+userIP;
		//String http_url ="http://freegeoip.net/json/"+userIP;
		URL url, obj;
		List <Double> coord = new ArrayList<>();
		try {
			url = new URL(http_url);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			conn.setInstanceFollowRedirects(false);
			conn.setFollowRedirects(false);
			int responseCode = conn.getResponseCode();
			String raspuns=Utilitare.parseInputStream(conn.getInputStream());
			JSONObject objj = (JSONObject) new JSONParser().parse(raspuns);
			Double latitude = ((Double) objj.get("lat"));
			Double longitude = ((Double) objj.get("lon"));
			coord.add(latitude);
			coord.add(longitude);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coord;
	}
}
