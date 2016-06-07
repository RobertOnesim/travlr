package flights1.flights1.user;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import flights1.flights1.Utilitare;

public class Facebook {
	
	public static String getPhoto(String accessToken,String userId){
		String https_url ="https://graph.facebook.com/v2.6/"+userId+"/picture"+
				"?access_token="+accessToken;
		/*String https_url ="https://graph.facebook.com/v2.6/1206723179359743/picture"+
				"?access_token=EAAGs3pTUYkABADYlmbswWcCVkGTebH6r2awxaVBL3y6CR71geUADZB4eNT87k413g1mZAfu7MpMD6CLRfZAtItuZCimeaDtbxMMiukbE0C3NuxqVBHYgl6xEfISN6weZBpL9ZBqzKKdV5I0b8TpzUqWu9JhBUu5Rt849ccJzYaLwZDZD";
		*/
		URL url;
		URL obj;
		String urlPoza = null;
		try {
			url = new URL(https_url);
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			//conn.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded");
			//		conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			conn.setInstanceFollowRedirects(false);
			conn.setFollowRedirects(false);
			int responseCode = conn.getResponseCode();
			urlPoza = conn.getHeaderField("Location");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return urlPoza;
	}
	
	public static JSONObject getEvents(String accessToken,String userId){
		JSONObject events = null;
		String https_url ="https://graph.facebook.com/v2.6/"+userId+"/events?fields=start_time,place"+
				"&access_token="+accessToken;
		/*String https_url ="https://graph.facebook.com/v2.6/1206723179359743/events?fields=start_time,place"+
				"&access_token=EAAGs3pTUYkABAM8SftZAkONMitFeFYS96QWBIZCcnDZCo3HRC8uuWt29VPsGSLGSVCtfDlmOBcQrnQIZCtObxZBZAZB1ZCD4B0Y9KcxfVrFg0rIxDeEpNBqjGmY7wZAzrJUvUB6RmlUGaLqMryg3fZAWtcmhVNsT8XFxfibXEIDV9ZCMQZDZD";
		*/
		//"http://www.graph.facebook.com/v2.6/1206723179359743/events";
		//" me?fields=id,name,events{start_time,place}";
		URL url;
		URL obj;
		try {
			url = new URL(https_url);
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			//conn.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded");
			//		conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = conn.getResponseCode();
			String raspunsEvents=Utilitare.parseInputStream(conn.getInputStream());
			events = (JSONObject) new JSONParser().parse(raspunsEvents);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return events;
	}
	
	public static String getName(String accessToken,String userId){
		JSONObject date = null;
		String https_url ="https://graph.facebook.com/v2.6/"+userId+"?access_token="+accessToken;
		//String https_url ="https://graph.facebook.com/v2.6/1206723179359743?access_token=EAAGs3pTUYkABAM8SftZAkONMitFeFYS96QWBIZCcnDZCo3HRC8uuWt29VPsGSLGSVCtfDlmOBcQrnQIZCtObxZBZAZB1ZCD4B0Y9KcxfVrFg0rIxDeEpNBqjGmY7wZAzrJUvUB6RmlUGaLqMryg3fZAWtcmhVNsT8XFxfibXEIDV9ZCMQZDZD";
		URL url;
		URL obj;
		try {
			url = new URL(https_url);
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = conn.getResponseCode();
			String raspunsDate=Utilitare.parseInputStream(conn.getInputStream());
			date = (JSONObject) new JSONParser().parse(raspunsDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (String)date.get("name");
	}

	public static List <EventPlaceDate> prelucrareJson (JSONObject eventsJSON){
		List <EventPlaceDate> events = new ArrayList<>();
		JSONArray data = (JSONArray)eventsJSON.get("data");
		for (Object element: data){
			EventPlaceDate event = new EventPlaceDate();
			JSONObject place = (JSONObject) ((JSONObject)element).get("place");
			if (place!=null){
				JSONObject location = (JSONObject) ((JSONObject)place).get("location");
				if (location!=null){
					Double latitude = (Double) ((JSONObject)location).get("latitude");
					Double longitude = (Double) ((JSONObject)location).get("longitude");
					String date = ((JSONObject)element).get("start_time").toString();
					date=date.substring(0, 10);
					event.setLatitudine(latitude);
					event.setLongitudine(longitude);
					event.setData(date);
					SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
					Date now = new Date();
					String strDate = sdfDate.format(now);
					if (date.compareTo(strDate)>=0) //AICI ESTE FILTRUL !!!!!!!!!!!
						events.add(event);
				}
			}
		}
		return events;
	}
}
