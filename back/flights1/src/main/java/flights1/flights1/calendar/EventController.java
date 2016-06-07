package flights1.flights1.calendar;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class EventController {
	@RequestMapping("/calendar/delete")
	public void deleteEvent(@RequestParam(required=true) String userId,@RequestParam(required=true) Integer groupId){
		ManagerEvent.deleteEvent(userId, groupId);
	}
	
	@RequestMapping("/calendar/insert")
	public void insertEvent(@RequestParam(required=true) String userId,@RequestParam(required=true) Integer groupId, @RequestParam(required=true) String name, @RequestParam(required=true) String date){
		ManagerEvent.insertEvent(userId, groupId, name, date);
	}

}
