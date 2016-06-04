package flights1.flights1.user;

public class Recomandation {
	private String departudeIata;
	private String departureCity;
	private String arrivalIata;
	private String arrivalCity;
	private String date;
	public Recomandation(String departureIata, String departureCity, String arrivalIata, String arrivalCity, String date){
		this.departudeIata=departureIata;
		this.departureCity=departureCity;
		this.arrivalIata=arrivalIata;
		this.arrivalCity=arrivalCity;
		this.date=date;
		
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
