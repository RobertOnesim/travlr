package flights1.flights1.user;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")

public class FacebookController {
	@RequestMapping("/events")
	public JSONObject oferireEvents(){
		return Facebook.getEvents();
	}
}
