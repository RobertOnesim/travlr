package flights1.flights1.user;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	@RequestMapping("/groupUsers")
	public @ResponseBody List<User> giveUsers (@RequestParam(required=true) int groupId) {
		System.out.println("FAC CEVA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		ManagerUser mu = new ManagerUser();
		List <User> usersFromGroup = mu.getUsersFromGroup(groupId);
		/*JSONObject o = new JSONObject();
		o.put("1", 5);
		return o;*/
		return usersFromGroup;
		//return (User)usersFromGroup.get(0);
	}
	
	@RequestMapping("/addUserToGroup")
	public void addUserToGroup(@RequestParam(required=true) String userId, @RequestParam(required=true) int groupId){
		ManagerUser mu = new ManagerUser();
		mu.addUserToGroup(userId, groupId);
	}

}
