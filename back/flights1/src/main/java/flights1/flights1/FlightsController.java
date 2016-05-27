package flights1.flights1;
import java.util.concurrent.atomic.AtomicLong;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class FlightsController {
	@RequestMapping("/flight") //?????????GET????????????!!!!!!!!!!!!!!!!!!!!!!!!!!
	public JSONObject prostie() {
		System.out.println("AM AJUNS AICIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
		JSONObject rawResponse=null, processedResponse=null;
		try {
			rawResponse = Flights.giveSolution();
			processedResponse = (new FlightsProcessor()).process(rawResponse); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Si AICI!!!!!!!!!!!");
		return processedResponse;
		//return new Greeting(counter.incrementAndGet(),
		//		String.format(template, name));
	}

}
