package flights1.flights1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ch.qos.logback.core.net.SyslogOutputStream;

public class FlightsProcessor {
	private Map <Integer, Carrier> carriers= new HashMap <Integer, Carrier>();
	private Map <Integer, Place> places= new HashMap <Integer, Place>();
	
	class Carrier{
		private Integer id;
		private String ImageUrl, name;
		public Carrier(Integer id, String imageUrl, String name){
			this.id=id;
			this.ImageUrl=imageUrl;
			this.name=name;
		}
		public Integer getId() {
			return id;
		}
		public String getImageUrl() {
			return ImageUrl;
		}
		public String getName() {
			return name;
		}
		
	}
	
	class Place{
		int id;
		String name;
		public Place(int id, String name){
			this.id=id;
			this.name=name;
		}
		public int getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		
	}
	
	void populareCarriers(JSONObject object){
		JSONArray carriersJson = (JSONArray) object.get("Carriers");
		Iterator<JSONObject> iterator = carriersJson.iterator();
		while (iterator.hasNext()) {
			JSONObject carrierJson = iterator.next();
			System.out.println(carrierJson.toString());
			Integer id=((Long)carrierJson.get("Id")).intValue();
			carriers.put(id,new Carrier(id,(String)(carrierJson.get("ImageUrl")),(String)(carrierJson.get("Name"))));
			//Integer id = ((Long)obj.get("Id")).intValue();
			System.out.println("carrier "+id);
		}
	}
	
	void popularePlaces(JSONObject object){
		JSONArray placesJson = (JSONArray) object.get("Places");
		Iterator<JSONObject> iterator = placesJson.iterator();
		while (iterator.hasNext()) {
			JSONObject placeJson = iterator.next();
			System.out.println(placeJson.toString());
			Integer id=((Long)placeJson.get("Id")).intValue();
			places.put(id,new Place(id,(String)(placeJson.get("Name"))));
			//Integer id = ((Long)obj.get("Id")).intValue();
			System.out.println("place "+id+" " +(String)(placeJson.get("Name")));
		}
	}
	
	public JSONObject process(JSONObject object){
		JSONObject processedObject = new JSONObject();
		JSONArray flights = new JSONArray();
		populareCarriers(object);
		popularePlaces(object);
		System.out.println(carriers.size()+" carriers si "+ places.size()+" places");
		JSONArray segmentsJson = (JSONArray) object.get("Segments");
		Iterator<JSONObject> iterator = segmentsJson.iterator();
		int numberOfSegments=0;
		while (iterator.hasNext()) {
			numberOfSegments++;
			if (numberOfSegments==100)
				break;
			JSONObject segmentJson = iterator.next();
			JSONObject flight = new JSONObject();
			JSONObject originStation = new JSONObject();
			Integer idOSt=((Long)segmentJson.get("OriginStation")).intValue();
			System.out.println(idOSt+"id statie");
			originStation.put("Name", (places.get(idOSt).getName()));
			flight.put("OriginStation", originStation);
			
			JSONObject destinationStation = new JSONObject();
			Integer idDSt=((Long)segmentJson.get("DestinationStation")).intValue();
			System.out.println(idDSt+"id statie2");
			destinationStation.put("Name", (places.get(idDSt).getName()));
			flight.put("DestinationStation", destinationStation);
			
			flight.put("DepartureDateTime",segmentJson.get("DepartureDateTime"));
			flight.put("ArrivalDateTime",segmentJson.get("ArrivalDateTime"));
			
			JSONObject carrier = new JSONObject();
			Integer idCarrier=((Long)segmentJson.get("Carrier")).intValue();
			carrier.put("Name", (carriers.get(idCarrier).getName()));
			carrier.put("ImageUrl", (carriers.get(idCarrier).getName()));
			flight.put("Carrier", carrier);
			
			Integer id=((Long)segmentJson.get("Id")).intValue();
			flight.put("Id",id);
			flights.add(flight);
		}
		System.out.println(flights.size()+"zboruri in json!!!!!!");
		processedObject.put("Flights",flights);
		//System.out.println(carriers.size());
		return processedObject;
	}

}
