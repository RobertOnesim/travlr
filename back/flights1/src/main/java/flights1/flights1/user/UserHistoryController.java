package flights1.flights1.user;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class UserHistoryController {
	@RequestMapping("/addToUserHistory")
	public void addToUserHistory(@RequestParam(required=true) String idUser,@RequestParam(required=true)String departureId,@RequestParam(required=true) String arrivalId){
		ManagerUserHistory mu = new ManagerUserHistory();
		mu.addUserHistory(idUser, departureId, arrivalId);
	}
	
}
