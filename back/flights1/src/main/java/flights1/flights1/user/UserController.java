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
	
	@RequestMapping("/createGroup")
	public @ResponseBody Integer createGroup(@RequestParam(required=true) String groupName){
		ManagerUser mu = new ManagerUser();
		Integer idGroup = mu.createGroup(groupName);
		return idGroup;
	}
	
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
	
	@RequestMapping("/userGroups")
	public @ResponseBody List<Integer> giveGroups (@RequestParam(required=true) String userId) {
		System.out.println("FAC userGroups!");
		ManagerUser mu = new ManagerUser();
		List <Integer> groupsOfUser = mu.getGroupsOfUser(userId);
		/*JSONObject o = new JSONObject();
		o.put("1", 5);
		return o;*/
		return groupsOfUser;
		//return (User)usersFromGroup.get(0);
	}
	
	@RequestMapping("/addUserToGroup")
	public void addUserToGroup(@RequestParam(required=true) String userId, @RequestParam(required=true) int groupId){
		ManagerUser mu = new ManagerUser();
		mu.addUserToGroup(userId, groupId);
	}
	
	@RequestMapping("/removeUserFromGroup")
	public void removeUserFromGroup(@RequestParam(required=true) String userId, @RequestParam(required=true) int groupId){
		ManagerUser mu = new ManagerUser();
		mu.removeUserFromGroup(userId, groupId);
	}
	
	
	@RequestMapping("/usersNotInGroup")
	public @ResponseBody List<User> searchUserNotInGroup(@RequestParam(required=true) Integer groupId){
		ManagerUser mu = new ManagerUser();
		List <User> usersByName = mu.getUsersNotInGroup(groupId);
		return usersByName;
	}

	@RequestMapping("/users")
	public @ResponseBody List<User> searchUserByName(@RequestParam(required=true) String userName){
		ManagerUser mu = new ManagerUser();
		List <User> usersByName = mu.getUserByName(userName);
		return usersByName;
	}
	
	@RequestMapping("/usersNotInGroupByName")
	public @ResponseBody List<User> searchUserNotInGroupByName(@RequestParam(required=true) String userName, @RequestParam(required=true) Integer groupId){
		ManagerUser mu = new ManagerUser();
		List <User> usersByName = mu.getUsersNotInGroupByName(groupId,userName);
		return usersByName;
	}

}
