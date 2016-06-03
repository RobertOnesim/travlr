package flights1.flights1.user;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")

public class FacebookController {
	@RequestMapping("/events")
	public @ResponseBody List <EventPlaceDate> oferireEvents(){
		JSONObject eventsJson = Facebook.getEvents();
		List <EventPlaceDate> events = Facebook.prelucrareJson(eventsJson); 
		return events;
	}
}
