package flights1.flights1.user;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import flights1.flights1.Utilitare;

public class Facebook {
	public static JSONObject getEvents(){
		JSONObject events = null;
		String https_url ="https://graph.facebook.com/v2.6/1206723179359743/events?fields=start_time,place"+
				"&access_token=EAAGs3pTUYkABABAS3rZA0nmIqrRimC8YCN2rVuQmFJ3NzXVhmZCPLg6n9Qvaow5of1BlLuN6ZAdlIIGPjEbxV2zPXje5fgJNASkdlkxfpGfFbUT1TtWBJk8EGACPYyzGvlI0MlWlH4ccSZCVMkS3qMaZAAFRuIVcLZCvfvsXMeEwZDZD";

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
			String raspunsZboruri=Utilitare.parseInputStream(conn.getInputStream());
			events = (JSONObject) new JSONParser().parse(raspunsZboruri);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return events;
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
