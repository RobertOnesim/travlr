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
	private Map <Integer, JSONObject> segments = new HashMap <Integer, JSONObject>();
	private Map <String, Double> itineraries = new HashMap <String, Double>();
	
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
			Integer id=((Long)carrierJson.get("Id")).intValue();
			carriers.put(id,new Carrier(id,(String)(carrierJson.get("ImageUrl")),(String)(carrierJson.get("Name"))));
		}
	}
	
	void popularePlaces(JSONObject object){
		JSONArray placesJson = (JSONArray) object.get("Places");
		Iterator<JSONObject> iterator = placesJson.iterator();
		while (iterator.hasNext()) {
			JSONObject placeJson = iterator.next();
			Integer id=((Long)placeJson.get("Id")).intValue();
			places.put(id,new Place(id,(String)(placeJson.get("Name"))));
		}
	}
	
	void populareSegments(JSONObject object){
		JSONArray segmentsJson = (JSONArray) object.get("Segments");
		Iterator<JSONObject> iterator = segmentsJson.iterator();
		while (iterator.hasNext()) {
			JSONObject segmentJson = iterator.next();
			Integer id=((Long)segmentJson.get("Id")).intValue();
			segments.put(id,segmentJson);
		}
	}
	
	void populareItineraries(JSONObject object){
		JSONArray itinerariesJson = (JSONArray) object.get("Itineraries");
		Iterator<JSONObject> iterator = itinerariesJson.iterator();
		while (iterator.hasNext()) {
			JSONObject itinerarJson = iterator.next();
			String id=(String) itinerarJson.get("OutboundLegId");
			JSONArray prices = (JSONArray)itinerarJson.get("PricingOptions");
			Double pret=((Double)((JSONObject)prices.get(0)).get("Price"));
			itineraries.put(id,pret);
		}
	}
	
	private JSONObject giveFlight(JSONObject segmentJson ){
		JSONObject flight = new JSONObject();
		JSONObject originStation = new JSONObject();
		Integer idOSt=((Long)segmentJson.get("OriginStation")).intValue();
		originStation.put("Name", (places.get(idOSt).getName()));
		flight.put("OriginStation", originStation);
		
		JSONObject destinationStation = new JSONObject();
		Integer idDSt=((Long)segmentJson.get("DestinationStation")).intValue();
		destinationStation.put("Name", (places.get(idDSt).getName()));
		flight.put("DestinationStation", destinationStation);
		
		flight.put("DepartureDateTime",segmentJson.get("DepartureDateTime"));
		flight.put("ArrivalDateTime",segmentJson.get("ArrivalDateTime"));
		
		JSONObject carrier = new JSONObject();
		Integer idCarrier=((Long)segmentJson.get("Carrier")).intValue();
		carrier.put("Name", (carriers.get(idCarrier).getName()));
		carrier.put("ImageUrl", (carriers.get(idCarrier).getImageUrl()));
		flight.put("Carrier", carrier);
		
		Integer id=((Long)segmentJson.get("Id")).intValue();
		flight.put("Id",id);
		return flight;
	}
	
	public JSONObject process(JSONObject object, Integer maxPrice){
		JSONObject processedObject = new JSONObject();
		JSONArray options = new JSONArray();
		populareCarriers(object);
		popularePlaces(object);
		populareSegments(object);
		populareItineraries(object);
		JSONArray legsJson = (JSONArray) object.get("Legs");
		Iterator<JSONObject> iterator = legsJson.iterator();
		while (iterator.hasNext()) {
			JSONObject legJson = iterator.next();
			JSONObject option = new JSONObject();
			String id=(String)legJson.get("Id");
			option.put("Id", id);
			option.put("Price", itineraries.get(id));
			if (maxPrice!=null)
				if (((Double)(itineraries.get(id))).doubleValue()>maxPrice)
					break;
			JSONArray segmentIdsJson = (JSONArray) legJson.get("SegmentIds");
			JSONArray segmentsToPut = new JSONArray();
			Iterator <Long> iterator1 = segmentIdsJson.iterator();
			while (iterator1.hasNext()){
				JSONObject segmentJson = segments.get(((Long)iterator1.next()).intValue());
				segmentsToPut.add(giveFlight(segmentJson));
			}
			option.put("Segments", segmentsToPut);
			options.add(option);
		}
		processedObject.put("Options", options);
		return processedObject;
	}

}
