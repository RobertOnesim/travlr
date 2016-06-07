package flights1.flights1.weatherapi.web;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherApiController {

	@RequestMapping("/api/weather/now")
	public String oferireVremeAcuma(){
		return flights1.flights1.weatherapi.web.Weather.getWeatherNow("London","UK");
	}
	
	@RequestMapping("/api/weather/weekly")
	public String oferireVremeSaptamana(){
		return flights1.flights1.weatherapi.web.Weather.getWeatherForecast("London",8);
	}

}