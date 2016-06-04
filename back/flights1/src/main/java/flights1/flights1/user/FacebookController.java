package flights1.flights1.user;

import java.util.List;

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
	public @ResponseBody List <EventPlaceDate> oferireEvents(){
		JSONObject eventsJson = Facebook.getEvents();
		List <EventPlaceDate> events = Facebook.prelucrareJson(eventsJson); 
		return events;
	}
	
	@RequestMapping("/photo")
	public String oferirePoza(){
		return (String) Facebook.getPhoto();
	}
	
	@RequestMapping("/login")
	public void login(@RequestParam(required=true) String idUser){/*,  @RequestHeader("userToken") String userToken){*/
		ManagerUser mu = new ManagerUser();
	//	System.out.println(userToken);
		User user = mu.getUserById(idUser);
		if (user==null){
			String nume = Facebook.getName(idUser);
			String imgUrl = Facebook.getPhoto();
			
			//System.out.println(nume);
			mu.addUser(idUser, nume, imgUrl);
		}else{
			System.out.println("in controller "+user.getId()+user.getLastName()+user.getImgUrl());
		}
		
	}
}
