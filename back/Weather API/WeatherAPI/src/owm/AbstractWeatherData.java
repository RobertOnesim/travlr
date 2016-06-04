package owm;

import org.json.JSONObject;

abstract public class AbstractWeatherData {
	protected static final String JSON_DATE_TIME = "dt";
	protected static final String JSON_MAIN      = "main";
	protected static final String JSON_WIND      = "wind";
	protected static final String JSON_RAIN      = "rain";
	protected static final String JSON_SNOW      = "snow";


	static abstract public class Main {
		protected static final String JSON_TEMP     = "temp";
		protected static final String JSON_TEMP_MIN = "temp_min";
		protected static final String JSON_TEMP_MAX = "temp_max";
		protected static final String JSON_HUMIDITY = "humidity";
		protected static final String JSON_PRESSURE = "pressure";

		abstract public float getTemp ();
		abstract public float getTempMin ();
		abstract public float getTempMax ();
		abstract public float getPressure ();
		abstract public float getHumidity ();
	}

	static abstract public class Wind {
		protected static final String JSON_SPEED   = "speed";
		protected static final String JSON_DEG     = "deg";
		protected static final String JSON_GUST    = "gust";
		protected static final String JSON_VAR_BEG = "var_beg";
		protected static final String JSON_VAR_END = "var_end";

		abstract public float getSpeed ();
		abstract public int getDeg ();
		abstract public float getGust ();
		abstract public int getVarBeg ();
		abstract public int getVarEnd ();
	}

	private final long dateTime;

	public AbstractWeatherData (JSONObject json) {
		this.dateTime = json.optLong (WeatherData.JSON_DATE_TIME, Long.MIN_VALUE);
	}

	public long getDateTime () {
		return this.dateTime;
	}

	abstract public float getTemp ();
	
	abstract public float getHumidity ();

	abstract public float getPressure ();

	abstract public float getWindSpeed ();

	abstract public float getWindGust ();

	abstract public int getWindDeg ();

	abstract public int getRain ();
	
	abstract public int getSnow ();

	abstract public int getPrecipitation ();
}