package flights1.flights1.user;

public class Recomandation {
	private String departudeIata;
	private String departureCity;
	private String arrivalIata;
	private Integer arrivalLat;
	private Integer arrivalLong;
	private String arrivalCity;
	private String date;
	
	public Recomandation(String departureIata, String departureCity, String arrivalIata, String arrivalCity, String date){
		System.out.println(departureIata+departureCity+arrivalIata+arrivalCity+date);
		this.departudeIata=departureIata;
		this.departureCity=departureCity;
		this.arrivalIata=arrivalIata;
		this.arrivalCity=arrivalCity;
		this.date=date;
		System.out.println("ARRIVALIATA   "+arrivalIata);
		if (arrivalIata!=null){
			City city = Aeroports.getCityByCode(arrivalIata);
			System.out.println((city==null) + "CITY");
			System.out.println(city.getLatitude()+city.getLongitude() + " LAT LONG");
			this.arrivalLat = city.getLatitude();
			this.arrivalLong = city.getLongitude();
		}else{
			this.arrivalLat = ((Double)Aeroports.getLatByName(arrivalCity)).intValue();
			this.arrivalLong = ((Double)Aeroports.getLngByName(arrivalCity)).intValue();
		}
	}
	
	public Integer getArrivalLat() {
		return arrivalLat;
	}
	public void setArrivalLat(Integer arrivalLat) {
		this.arrivalLat = arrivalLat;
	}
	public Integer getArrivalLong() {
		return arrivalLong;
	}
	public void setArrivalLong(Integer arrivalLong) {
		this.arrivalLong = arrivalLong;
	}
	public String getDepartudeIata() {
		return departudeIata;
	}
	public void setDepartudeIata(String departudeIata) {
		this.departudeIata = departudeIata;
	}
	public String getDepartureCity() {
		return departureCity;
	}
	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}
	public String getArrivalIata() {
		return arrivalIata;
	}
	public void setArrivalIata(String arrivalIata) {
		this.arrivalIata = arrivalIata;
	}
	public String getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
