package flights1.flights1;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.net.SyslogOutputStream;

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
									 @RequestParam(value="max-price", required = false) Integer maxPrice){
		//System.out.println("AM AJUNS AICIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
		JSONObject rawResponse=null, processedResponse=null;
		//JSONArray a = new JSONArray();
		//a.add(myParams);
		try {
	
			rawResponse = Flights.giveSolution(departureCity, arrivalCity, departureDate, adults, children, infants, maxDuration);
			processedResponse = (new FlightsProcessor()).process(rawResponse,maxPrice);
		//	System.out.println(processedResponse.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Si AICI!!!!!!!!!!!");
		//processedResponse=null;
		//JSONObject processedResponse1=new JSONObject();
		//processedResponse1.put("vector", myParams);
		return processedResponse;
		//return new Greeting(counter.incrementAndGet(),
		//		String.format(template, name));
	}

}
