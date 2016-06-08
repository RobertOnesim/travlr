package flights1.flights1;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.net.SyslogOutputStream;
import flights1.flights1.user.Aeroports;
import flights1.flights1.user.ManagerUser;
import flights1.flights1.user.ManagerUserHistory;

@RestController
@CrossOrigin(origins = "*")
public class FlightsController {
	@RequestMapping("/flight") //?????????GET????????????!!!!!!!!!!!!!!!!!!!!!!!!!!
	//public JSONObject prostie(@RequestParam(value="city") String[] city) {
	public JSONObject oferireZboruri(@RequestParam(value="departure-city") String departureCity,
									 @RequestParam(value="arrival-city") String arrivalCity,
									 @RequestParam(value="departure-date") String departureDate,
									 @RequestParam(value="adults") Integer adults,
									 @RequestParam(value="children") Integer children,
									 @RequestParam(value="infants") Integer infants,
									 @RequestParam(value="max-duration", required = false) Integer maxDuration,
									 @RequestParam(value="max-price", required = false) Integer maxPrice,
									 @RequestParam(value="userId", required = false) String idUser){
		//System.out.println("AM AJUNS AICIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
		JSONObject rawResponse=null, processedResponse=null;
		JSONObject resultJ = new JSONObject();
		JSONArray result = new JSONArray();
		if (idUser!=null){
			ManagerUserHistory muh = new ManagerUserHistory();
			muh.addUserHistory(idUser, departureCity, arrivalCity);
		}
		//JSONArray a = new JSONArray();
		//a.add(myParams);
		try {
			List <String> codes1 = Aeroports.getAirportsByName(departureCity);
			List <String> codes2 = Aeroports.getAirportsByName(arrivalCity);
			for (int index1=0;index1<codes1.size()&&index1<=1;index1++)
				for (int index2=0;index2<codes2.size()&&index2<=1;index2++){
					try{
						rawResponse = Flights.giveSolution(codes1.get(index1), codes2.get(index2), departureDate, adults, children, infants, maxDuration);
						processedResponse = (new FlightsProcessor()).process(rawResponse,maxPrice);
						result.addAll((JSONArray)processedResponse.get("Options"));
					}catch(Exception e){
						
					}
				}
			//rawResponse = Flights.giveSolution(departureCity, arrivalCity, departureDate, adults, children, infants, maxDuration);
			//processedResponse = (new FlightsProcessor()).process(rawResponse,maxPrice);
		//	System.out.println(processedResponse.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Si AICI!!!!!!!!!!!");
		resultJ.put("Options",result);
		return resultJ;
		//processedResponse=null;
		//JSONObject processedResponse1=new JSONObject();
		//processedResponse1.put("vector", myParams);
		//return processedResponse;
		//return new Greeting(counter.incrementAndGet(),
		//		String.format(template, name));
	}

}
