package owm;

import java.io.IOException;

import org.bitpipeline.lib.owm.WeatherData.WeatherCondition;
import org.json.JSONException;

public class Main {

	public static void main(String args[]) throws IOException, JSONException
	{
		OwmClient owm = new OwmClient ();
		WeatherStatusResponse currentWeather = owm.currentWeatherAtCity ("Tokyo", "JP");
		if (currentWeather.hasWeatherStatus ()) {
			WeatherData weather = currentWeather.getWeatherStatus ().get (0);
			if (weather.getPrecipitation () == Integer.MIN_VALUE) {
				owm.WeatherData.WeatherCondition weatherCondition = weather.getWeatherConditions ().get (0);
				String description = weatherCondition.getDescription ();
				if (description.contains ("rain") || description.contains ("shower"))
					System.out.println ("No rain measures in Tokyo but reports of " + description);
				else
					System.out.println ("No rain measures in Tokyo: " + description);
			} else
				System.out.println ("It's raining in Tokyo: " + weather.getPrecipitation () + " mm/h");
		}
	}
}
