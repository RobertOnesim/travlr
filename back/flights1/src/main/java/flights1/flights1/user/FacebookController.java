package flights1.flights1.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")

public class FacebookController {
	@RequestMapping("/events")
	public @ResponseBody List <EventPlaceDate> oferireEvents(@RequestParam(required=true) String accessToken, @RequestParam(required=true) String userId){
		JSONObject eventsJson = Facebook.getEvents(accessToken, userId);
		List <EventPlaceDate> events = Facebook.prelucrareJson(eventsJson); 
		return events;
	}
	
	@RequestMapping("/photo")
	public String oferirePoza(@RequestParam(required=true) String accessToken, @RequestParam(required=true) String userId){
		return (String) Facebook.getPhoto(accessToken, userId);
	}
	
	@RequestMapping("/loginGoogle")
	public void loginGoogle( @RequestParam(required=true) String userId, @RequestParam(required=true) String userName, @RequestParam(required=true) String imgUrl){
		ManagerUser mu = new ManagerUser();
		User user = mu.getUserById(userId);
		if ((user==null)){
			mu.addUser(userId, userName, imgUrl);
		}
	}
	
	@RequestMapping("/login")
	public void login(@RequestParam(required=true) String accessToken, @RequestParam(required=true) String userId){/*,  @RequestHeader("userToken") String userToken){*/
		ManagerUser mu = new ManagerUser();
		User user = mu.getUserById(userId);
		if ((user==null)){
			if (userId.contains("@")){
				String nume = Facebook.getName(accessToken, userId);
				String imgUrl = Facebook.getPhoto(accessToken, userId);
				mu.addUser(userId, nume, imgUrl);
			}
		}else{
			System.out.println("in controller "+user.getId()+user.getLastName()+user.getImgUrl());
		}
		
	}
}
