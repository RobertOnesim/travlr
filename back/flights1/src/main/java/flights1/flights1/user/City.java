package flights1.flights1.user;

public class City {
	private String name;
	private Integer longitude;
	private Integer latitude;
	
	public City(){	
	}
	
	public City(String name, Double longitude, Double latitude){
		this.name=name;
	
		this.longitude = longitude.intValue();
		this.latitude = latitude.intValue();
	}

	public String getName() {
		return name;
	}

	public Integer getLongitude() {
		return longitude;
	}

	public Integer getLatitude() {
		return latitude;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}
	
	public void setLongitude(double longitude){
		this.longitude = ((Long)Math.round(longitude)).intValue();
	}
	
	public void setLatitude(double latitude){
		this.latitude = ((Long)Math.round(latitude)).intValue();
	}
	
}
