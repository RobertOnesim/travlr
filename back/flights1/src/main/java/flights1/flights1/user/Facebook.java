package flights1.flights1.user;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import flights1.flights1.Utilitare;

public class Facebook {
	public static JSONObject getEvents(){
		JSONObject events = null;
		String url="graph.facebook.com/v2.5/me?fields=id,name,events{start_time,place}&access_token=EAACEdEose0cBACX3utZBVCQkVC4g76T9XCoCWJnMgSWpLfwSlJFnOa7iyVdCE8Y1c8jLcVCltnBoCbmhPlANVsZBMRAbceZAIzyMO873ZCoSRCLEH42EPKRw9YWCJfpfGwKhlPJjKisDlH1ZAoQSskhaj2uQZBJRTOdCYLDY3WpAZDZD";
		URL obj;
		try {
			obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = conn.getResponseCode();
			String raspunsZboruri=Utilitare.parseInputStream(conn.getInputStream());
			events = (JSONObject) new JSONParser().parse(raspunsZboruri);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return events;
	}
}
