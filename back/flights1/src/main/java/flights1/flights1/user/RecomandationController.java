package flights1.flights1.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class RecomandationController {
	@RequestMapping("/offers")
	public @ResponseBody List<Recomandation> giveOffers(@RequestParam (required=false) String accessToken, @RequestParam (required=false) String userId, HttpServletRequest request){
		System.out.println(request.getRemoteAddr());
		List <Recomandation> recomandations=RecomandationGenerator.giveRecomandations(accessToken,  userId, request.getRemoteAddr());
		return recomandations;
	}
}
